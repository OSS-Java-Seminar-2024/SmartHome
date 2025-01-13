package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.User;
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

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {  // Check for existing username
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setRole("ROLE_USER"); // Default role
        return userRepository.save(user); // Save the user
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null); // Retrieve user by username
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {  // Check credentials
            throw new RuntimeException("Invalid credentials");
        }
        return user; // Return authenticated user
    }
}
