package com.project.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.property.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a registered user profile using their exact email string.
     * Essential for credential lookup loops during renter login.
     * 
     * @param email The login identifier email address
     * @return An Optional wrapper containing the User if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a specific email address already exists in the system.
     * Useful for throwing validation exceptions during renter registration.
     * 
     * @param email The email to check
     * @return true if the email is taken, false otherwise
     */
    boolean existsByEmail(String email);
}