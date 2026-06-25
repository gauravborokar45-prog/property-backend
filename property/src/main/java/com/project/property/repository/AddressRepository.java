package com.project.property.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.property.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCityIgnoreCase(String city);
}
