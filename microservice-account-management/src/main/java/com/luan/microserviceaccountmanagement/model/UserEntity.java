package com.luan.microserviceaccountmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String encryptedPassword;
    private boolean enabled;
    private boolean locked;
    private String email;
    private String role; //Eg: USER, ADMIN
    public UserEntity(String userName, String encryptedPassword, String role, boolean enabled) {
        super();
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
        this.enabled = enabled;
    }
}
