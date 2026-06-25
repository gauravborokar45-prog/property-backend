package com.project.property.repository;

import com.project.property.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    
    // Existing method for Phone lookup
    Optional<Owner> findByPhone(String phone);

    // Added method for Email lookup
    Optional<Owner> findByEmail(String email);
}