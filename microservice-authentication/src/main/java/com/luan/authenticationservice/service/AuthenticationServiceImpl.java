package com.luan.authenticationservice.service;

import com.luan.authenticationservice.dto.TokenDTO;
import com.luan.authenticationservice.mapper.TokenMapper;
import com.luan.authenticationservice.mapper.UserMapper;
import com.luan.authenticationservice.model.TokenEntity;
import com.luan.authenticationservice.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.luan.authenticationservice.dto.AuthenticationResponse;
import com.luan.authenticationservice.dto.UserDTO;
import com.luan.authenticationservice.model.UserEntity;
import com.luan.authenticationservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private UserMapper userMapper;
	private TokenMapper tokenMapper;

	public AuthenticationResponse register(UserDTO request) {
		UserEntity userEntity = userMapper.mapToUserEntity(request);
		userEntity.setEncryptedPassword(passwordEncoder.encode(request.getEncryptedPassword()));
		UserEntity user = repository.save(userEntity);
		String jwt = jwtService.generateToken(user);

		saveUserToken(jwt, user);

		return new AuthenticationResponse(jwt, "User registration was successful");
	}
	
	public AuthenticationResponse authenticate(UserDTO request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getUserName(),
				request.getEncryptedPassword()
			)
		);

		UserEntity user = repository.findByUserName(request.getUserName()).orElseThrow();
		String jwt = jwtService.generateToken(user);

		revokeAllTokenByUser(user);
		saveUserToken(jwt, user);

		return new AuthenticationResponse(jwt, "User login was successful");
	}

	@Override
	public String lockUser(String username) {
		Optional<UserEntity> findUser = repository.findByUserName(username);
		if (findUser.isPresent()) {
			UserEntity user = findUser.get();
			user.setLocked(true);
			repository.save(user);
			return "Account '" + username + "' blocked.";
		} else {
			return "No account found with username '" + username + "'.";
		}
	}

	@Override
	public String processForgotPassword(String email) {
		Optional<UserEntity> findUser = repository.findByEmail(email);
		if (findUser.isPresent()) {
			UserEntity user = findUser.get();
			// Generate a new random password
			String newPassword = UUID.randomUUID().toString();
			user.setEncryptedPassword(passwordEncoder.encode(newPassword));
			repository.save(user);

			// Send an email containing the new password
			sendResetPasswordEmail(user.getEmail(), newPassword);
			return "New password reset email sent " + newPassword + " to the address " + user.getEmail();
		} else {
			return "No account found with email '" + email + "'.";
		}
	}

	@Override
	public void sendResetPasswordEmail(String toEmail, String newPassword) {
		System.out.println("New password reset email sent " + newPassword + " to the address " + toEmail);
	}

	@Override
	public List<TokenDTO> viewActiveAccounts() {
		return tokenMapper.mapToTokenDTOs(tokenRepository.findAllByIsLoggedOutFalse());
	}

	private void revokeAllTokenByUser(UserEntity user) {
		List<TokenEntity> validTokens = tokenRepository.findAllTokensByUser(user.getUserId());
		if(validTokens.isEmpty()) {
			return;
		}

		validTokens.forEach(t-> {
			t.setLoggedOut(true);
		});

		tokenRepository.saveAll(validTokens);
	}
	private void saveUserToken(String jwt, UserEntity user) {
		TokenEntity token = new TokenEntity();
		token.setToken(jwt);
		token.setLoggedOut(false);
		token.setUser(user);
		tokenRepository.save(token);
	}


}