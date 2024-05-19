package com.luan.authenticationservice.service;

import com.luan.authenticationservice.dto.AuthenticationResponse;
import com.luan.authenticationservice.dto.TokenDTO;
import com.luan.authenticationservice.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthenticationService {
	AuthenticationResponse register(UserDTO request);
	AuthenticationResponse authenticate(UserDTO request);
	String lockUser(String username);
	String processForgotPassword(String email);
	void sendResetPasswordEmail(String toEmail, String resetPasswordLink);
	List<TokenDTO> viewActiveAccounts();
}
