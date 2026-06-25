package com.project.property.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.property.entity.User;
import com.project.property.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allows communication with your React frontend development server port
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint: POST /api/users/register
     * Handles new tenant account creation.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            // Return 201 Created on successful persistence
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Catches validation faults like duplicate emails
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected server error occurred during registration.");
        }
    }

    /**
     * Endpoint: POST /api/users/login
     * Processes tenant authentication requests securely.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");

            // Simple request validation mapping
            if (email == null || password == null) {
                return ResponseEntity.badRequest().body("Email and password fields are mandatory parameters.");
            }

            User authenticatedUser = userService.loginUser(email, password);
            return ResponseEntity.ok(authenticatedUser);
        } catch (RuntimeException e) {
            // Catches incorrect passwords or missing email records gracefully (Returns a 401 Unauthorized status)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected server error occurred during login verification.");
        }
    }
}