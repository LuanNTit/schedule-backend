package com.luan.authenticationservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "token")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;
    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}

