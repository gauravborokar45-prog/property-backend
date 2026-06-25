package com.project.property.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.property.dto.CreateBasicPropertyDto;
import com.project.property.dto.CreatePropertyDto;
import com.project.property.entity.Address;
import com.project.property.entity.Image;
import com.project.property.entity.Owner;
import com.project.property.entity.Property;
import com.project.property.enums.Furnish;
import com.project.property.enums.Gender;
import com.project.property.enums.PropertyType;
import com.project.property.repository.OwnerRepository;
import com.project.property.repository.PropertyRepository;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Property createBasicProperty(CreateBasicPropertyDto dto) {
        Property property = mapDtoToProperty(dto);
        return propertyRepository.save(property);
    }

    public Property createProperty(CreatePropertyDto dto) {
        Property property = mapDtoToProperty(dto);

        // Set Address
        property.setAddress(dto.getAddress());

        // Set Images
        List<Image> images = new ArrayList<>();
        if (dto.getImageUrls() != null) {
            for (String url : dto.getImageUrls()) {
                Image image = new Image();
                image.setImgUrl(url);
                image.setProperty(property);
                image.setCreatedAt(LocalDateTime.now());
                images.add(image);
            }
        }
        property.setImages(images);

        return propertyRepository.save(property);
    }

    private Property mapDtoToProperty(Object dto) {
        Property property = new Property();
        property.setCreatedAt(LocalDateTime.now());

        if (dto instanceof CreateBasicPropertyDto basic) {
            property.setName(basic.getName());
            property.setType(basic.getType());
            property.setRent(basic.getRent());
            property.setDeposit(basic.getDeposit());
            property.setAvailable(true);
            property.setGender(basic.getGender());
            property.setFurnishing(basic.getFurnishing());
            property.setLocationUrl(basic.getLocationUrl());
            property.setNoOfVacancies(basic.getNoOfVacancies());
            property.setBhk(basic.getBhk());
            property.setTwoWheelerParking(Boolean.TRUE.equals(basic.getTwoWheelerParking()));
            property.setFourWheelerParking(Boolean.TRUE.equals(basic.getFourWheelerParking()));
            Owner owner = ownerRepository.findById(basic.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            property.setOwner(owner);
        } else if (dto instanceof CreatePropertyDto full) {
            property.setName(full.getName());
            property.setType(full.getType());
            property.setRent(full.getRent());
            property.setDeposit(full.getDeposit());
            property.setGender(full.getGender());
            property.setFurnishing(full.getFurnishing());
            property.setLocationUrl(full.getLocationUrl());
            property.setNoOfVacancies(full.getNoOfVacancies());
            property.setBhk(full.getBhk());
            property.setTwoWheelerParking(Boolean.TRUE.equals(full.getTwoWheelerParking()));
            property.setFourWheelerParking(Boolean.TRUE.equals(full.getFourWheelerParking()));
            Owner owner = ownerRepository.findById(full.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            property.setOwner(owner);
        }

        return property;
    }

    public Property addAddress(Long propertyId, Address address) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        property.setAddress(address);
        return propertyRepository.save(property);
    }

    public Property addImages(Long propertyId, List<String> imageUrls) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        List<Image> images = new ArrayList<>();
        for (String url : imageUrls) {
            Image image = new Image();
            image.setImgUrl(url);
            image.setCreatedAt(LocalDateTime.now());
            image.setProperty(property);
            images.add(image);
        }
        property.setImages(images);
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<Property> getFilteredProperties(String city, String area, PropertyType type,
                                                Gender gender, Furnish furnishing,
                                                Double minPrice, Double maxPrice) {
        return propertyRepository.filterProperties(city, area, type, gender, furnishing, minPrice, maxPrice);
    }

    public Page<Property> getFilteredPropertiesPaged(String city, String area, PropertyType type,
                                                     Gender gender, Furnish furnishing,
                                                     Double minPrice, Double maxPrice,
                                                     int page, int size) {
        return propertyRepository.filterPropertiesPaged(
                city, area, type, gender, furnishing, minPrice, maxPrice,
                PageRequest.of(page, size)
        );
    }
    
    public Property updateAvailability(Long propertyId, boolean available) {
    	Property property = propertyRepository.findById(propertyId)
    		    .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));
        
        property.setAvailable(available);

        // Update createdAt only when available is set to true
        if (available) {
            property.setCreatedAt(LocalDateTime.now());
        }

        return propertyRepository.save(property);
    }
    
    /**
     * Retrieves all properties associated with an owner's phone number.
     * 
     * @param phone The 10-digit phone number of the property owner
     * @return List of properties belonging to that owner
     */
    public List<Property> getPropertiesByOwnerPhone(String phone) {
        // Optional verification: verify the owner exists before returning empty sets
        if (!ownerRepository.findByPhone(phone).isPresent()) {
            throw new RuntimeException("Owner profile with phone number " + phone + " does not exist.");
        }
        
        return propertyRepository.findByOwnerPhone(phone);
    }
    
    /**
     * Deletes a property after verifying that the requesting owner 
     * actually owns the property unit.
     * * @param propertyId The ID of the property to be deleted
     * @param ownerId The ID of the authenticated owner making the request
     */
    public void deleteProperty(Long propertyId, Long ownerId) {
        // 1. Fetch the property record
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));

        // 2. Security Check: Verify ownership before executing the delete
        if (!property.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized: You do not have permission to delete this property.");
        }

        // 3. Clear database record
        propertyRepository.delete(property);
    }
}
