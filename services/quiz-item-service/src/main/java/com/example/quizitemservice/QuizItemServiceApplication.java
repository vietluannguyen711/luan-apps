package com.example.quizitemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class QuizItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizItemServiceApplication.class, args);
    }

}
