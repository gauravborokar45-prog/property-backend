package com.project.property.service;

import com.project.property.entity.Address;
import com.project.property.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<String> getAreasByCity(String city, String query) {
        List<Address> addresses = addressRepository.findByCityIgnoreCase(city);

        return addresses.stream()
                .map(Address::getArea)
                .filter(area -> area != null && !area.isEmpty())
                .filter(area -> query == null || area.toLowerCase().contains(query.toLowerCase()))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
