package com.example.quizauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizAuthServiceApplication.class, args);
    }

}
