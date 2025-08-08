package com.example.quizauthservice.service.client;

import com.example.quizauthservice.dto.LoginRequest;
import com.example.quizauthservice.dto.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(name = "quiz-user-service")
public interface UserServiceClient {
    @PostMapping("/users")
    void createUser(RegisterRequest registerRequest);
    @GetMapping("/users/username/{username}")
    LoginRequest getUserByUsername(@PathVariable String username);
}
