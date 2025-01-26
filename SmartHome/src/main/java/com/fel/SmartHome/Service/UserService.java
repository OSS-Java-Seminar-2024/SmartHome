package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.LoginRequest;
import com.fel.SmartHome.Model.RegisterRequest;
import com.fel.SmartHome.Model.Users;
import com.fel.SmartHome.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return null; // User already exists
        }
        var user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(registerRequest.getRole());
        return userRepository.save(user);
    }

    public Users authenticateUser(LoginRequest loginRequest) {
        Optional<Users> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return user; // Authentication successful
            }
        }
        return null; // Authentication failed
    }
}
