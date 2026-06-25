package com.project.property.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.property.entity.User;
import com.project.property.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Standard BCrypt password encoder instance
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Registers a new tenant/user securely into the database layer.
     * 
     * @param user The incoming user details container map
     * @return The persisted User entity object with ID and timestamps
     */
    public User registerUser(User user) {
        // 1. Validation Check: Make sure email address isn't already claimed
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("An account with this email address already exists.");
        }

        // 2. Encryption Step: Convert raw password string into a secure BCrypt string hash
        String securePasswordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(securePasswordHash);

        // 3. Persist and return clean entity tracking metrics
        return userRepository.save(user);
    }

    /**
     * Authenticates a renter login request against cryptographic DB records.
     * 
     * @param email The target identity string
     * @param rawPassword The clear-text verification password input from client
     * @return The fully populated User entity if authentication passes successfully
     */
    public User loginUser(String email, String rawPassword) {
        // 1. Fetch record by email identifier
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email address or password configuration."));

        // 2. Match incoming raw string to database hash string
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid email address or password configuration.");
        }

        // 3. Authentication successful. Clear password field in output instance for safety
        user.setPassword(null);
        return user;
    }
}