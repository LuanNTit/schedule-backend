package com.luan.authenticationservice.service;
import com.luan.authenticationservice.dto.UserDTO;
import com.luan.authenticationservice.model.UserEntity;
import com.luan.authenticationservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (userEntity.isLocked()) {
            throw new UsernameNotFoundException("User account is locked.");
        }
        return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getEncryptedPassword())
                .roles(getRoles(userEntity))
                .build();
    }
    public UserDTO convertToDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setEncryptedPassword(userEntity.getEncryptedPassword());
        userDTO.setRole(userEntity.getRole());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setEnabled(userEntity.isEnabled());
        return userDTO;
    }
    private String[] getRoles(UserEntity user){
        if (user.getRole() == null) {
            return new String[] {"USER"};
        }
        return user.getRole().split(",");
    }
}

