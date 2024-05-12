package com.example.group3pkg.Dto;


import com.example.group3pkg.models.Role;

import lombok.Data;

@Data
public class ResponseDto {
    private Long user_id;
    private String username;
    private String email;
    private Role role;
    private boolean verified;
    private String message;
}