package com.example.group3pkg.Dto;



import com.example.group3pkg.models.Role;

import lombok.Data;

@Data
public class RequestDto {
    private String username;
    private Role role;
    private String email;
}