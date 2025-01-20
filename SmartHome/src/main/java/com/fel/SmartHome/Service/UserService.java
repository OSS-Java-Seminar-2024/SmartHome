package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.Users;
import com.fel.SmartHome.Repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    // Constructor injection for dependencies
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Store plain text password (not recommended)
                .roles(user.getRole().name()) // Convert Role enum to String
                .build();
    }

    // Register a user without encoding the password
    public Users registerUser(Users user) {
        // Do not encode password, just save it as plain text
        return userRepository.save(user);
    }
}
