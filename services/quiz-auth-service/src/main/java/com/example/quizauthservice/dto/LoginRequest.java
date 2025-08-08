package com.example.quizauthservice.dto;

import com.example.quizauthservice.enums.Role;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private Role role;
}

