package com.luan.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String encryptedPassword;
    private boolean enabled;
    private boolean locked;
    private String email;
    private String role; //Eg: USER, ADMIN
}
