package com.example.springpractice;

import com.example.springpractice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserService userService) {
        return args -> {
            userService.createDefaultUserIfNotExists();
            userService.createDemoUsersIfNotExists();
        };
    }
}
