package com.example.springpractice.service;

import com.example.springpractice.entity.User;
import com.example.springpractice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
    }

    public void createDefaultUserIfNotExists() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User("admin", passwordEncoder.encode("password123"), "ADMIN");
            userRepository.save(user);
        }
    }

    public void createDemoUsersIfNotExists() {
        String[] demoUsernames = {
            "user01", "user02", "user03", "user04", "user05",
            "user06", "user07", "user08", "user09", "user10"
        };

        for (int i = 0; i < demoUsernames.length; i++) {
            String username = demoUsernames[i];
            if (userRepository.findByUsername(username).isEmpty()) {
                String password = "pass" + (i + 1);
                User user = new User(username, passwordEncoder.encode(password), "USER");
                userRepository.save(user);
            }
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean registerUser(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, String username, String password, String role) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        if (username == null || username.isBlank()) {
            return false;
        }

        if (!username.equals(user.getUsername()) && userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        user.setUsername(username);
        if (password != null && !password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (role != null && !role.isBlank()) {
            user.setRole(role.toUpperCase());
        }

        userRepository.save(user);
        return true;
    }

    public String getPlainPasswordForAdmin(String hashedPassword) {
        return hashedPassword;
    }
}
