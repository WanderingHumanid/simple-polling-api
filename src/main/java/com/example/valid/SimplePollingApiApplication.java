package com.example.valid;

import com.example.valid.User;
import com.example.valid.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SimplePollingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplePollingApiApplication.class, args);
    }

    // This runs automatically when the server starts
    @Bean
    public CommandLineRunner initData(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            // If no users exist, create the Super Admin
            if (userRepo.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123")); // Default password
                admin.setRole("ADMIN");
                userRepo.save(admin);
                System.out.println("SUPER ADMIN CREATED: Username: admin | Password: admin123");
            }
        };
    }
}