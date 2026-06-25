package com.project.property.service;

import com.project.property.entity.Owner;
import com.project.property.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Injected Encoder

    public Owner registerOwner(Owner owner) {
        Optional<Owner> existingOwner = ownerRepository.findByPhone(owner.getPhone());
        if (existingOwner.isPresent()) {
            throw new RuntimeException("Owner with phone " + owner.getPhone() + " already exists");
        }
        
        // Encrypt the password before saving it to the Database
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        
        return ownerRepository.save(owner);
    }

    public Optional<Owner> getOwnerByPhone(String phone) {
        return ownerRepository.findByPhone(phone);
    }

    /**
     * Authenticates an owner profile via email or phone string.
     * 
     * @param username The email or phone string inputted by the user
     * @param rawPassword The clear-text password input
     * @return The complete authenticated Owner data model object
     */
    public Owner loginOwner(String username, String rawPassword) {
        // 1. Try finding the owner profile by email string
        Optional<Owner> ownerOpt = ownerRepository.findByEmail(username);
        
        // 2. If not found by email, try looking up via their phone number
        if (ownerOpt.isEmpty()) {
            ownerOpt = ownerRepository.findByPhone(username);
        }

        // Throw unified error if both database lookups return empty
        if (ownerOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or password.");
        }

        Owner owner = ownerOpt.get();

        // 3. Use BCrypt to securely match the raw password against the database hash
        if (!passwordEncoder.matches(rawPassword, owner.getPassword())) {
            throw new RuntimeException("Invalid username or password.");
        }

        // Return the full object payload so your frontend has immediate access to ID, name, email, phone, etc.
        return owner;
    }
}