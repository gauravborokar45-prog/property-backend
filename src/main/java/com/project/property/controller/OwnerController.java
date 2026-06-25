package com.project.property.controller;

import com.project.property.dto.LoginRequest; // Assuming you created this DTO
import com.project.property.entity.Owner;
import com.project.property.service.OwnerService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // --- Existing Code ---

    @PostMapping("/register")
    public ResponseEntity<?> registerOwner(@RequestBody Owner owner) {
        try {
            Owner savedOwner = ownerService.registerOwner(owner);
            return ResponseEntity.ok("Owner registration successful with phone: " + savedOwner.getPhone());
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Owner> getOwnerByPhone(@RequestParam String phone) {
        return ownerService.getOwnerByPhone(phone)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // --- Added Login Endpoint ---

    @PostMapping("/login")
    public ResponseEntity<?> loginOwner(@RequestBody LoginRequest loginRequest) {
        try {
            // Service now passes back the full authenticated Owner record object
            Owner authenticatedOwner = ownerService.loginOwner(loginRequest.getUsername(), loginRequest.getPassword());
            
            // Returns status 200 OK containing the entire owner object payload
            return ResponseEntity.ok(authenticatedOwner);
        } catch (RuntimeException ex) {
            // Returns a 400 Bad Request with the exact failure message
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}