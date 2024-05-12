package com.example.group3pkg.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    private String otp;
    private boolean verified;
}




