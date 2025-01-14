package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.Users;
import com.fel.SmartHome.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Users registerUser(Users users) {
        if (userRepository.findByUsername(users.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole("ROLE_USER");
        return userRepository.save(users);
    }

    public Users loginUser(String username, String password) {
        Users users = userRepository.findByUsername(username).orElse(null);
        if (users == null || !passwordEncoder.matches(password, users.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return users;
    }
}
