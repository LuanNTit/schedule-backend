package com.luan.authenticationservice.dto;

import lombok.Data;

@Data
public class TokenDTO {
    private Long id;
    private String username;
    private UserDTO user;
    private String token;
    private boolean loggedOut;
}
