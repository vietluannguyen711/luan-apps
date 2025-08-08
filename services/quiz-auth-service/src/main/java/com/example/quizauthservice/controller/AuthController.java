package com.example.quizauthservice.controller;

import com.example.quizauthservice.dto.AuthResponse;
import com.example.quizauthservice.dto.LoginRequest;
import com.example.quizauthservice.dto.RegisterRequest;
import com.example.quizauthservice.service.client.UserServiceClient;
import com.example.quizauthservice.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userServiceClient.createUser(registerRequest);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        LoginRequest loggedInUser = userServiceClient.getUserByUsername(username);
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", loggedInUser.getRole().name());
        if (passwordEncoder.matches(loginRequest.getPassword(), loggedInUser.getPassword())) {
            return ResponseEntity.ok(AuthResponse.builder().token(jwtUtil.generateToken(claims, username)).build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
