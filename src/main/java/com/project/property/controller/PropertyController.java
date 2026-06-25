package com.project.property.controller;

import com.project.property.dto.CreateBasicPropertyDto;
import com.project.property.dto.CreatePropertyDto;
import com.project.property.entity.Address;
import com.project.property.entity.Property;
import com.project.property.enums.Furnish;
import com.project.property.enums.Gender;
import com.project.property.enums.PropertyType;
import com.project.property.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property> create(@RequestBody CreatePropertyDto dto) {
        Property property = propertyService.createProperty(dto);
        return ResponseEntity.ok(property);
    }

    @PostMapping("/basic")
    public ResponseEntity<Property> createBasic(@RequestBody CreateBasicPropertyDto dto) {
        Property property = propertyService.createBasicProperty(dto);
        return ResponseEntity.ok(property);
    }
    
    @PostMapping("/{propertyId}/address")
    public ResponseEntity<Property> addAddress(@PathVariable Long propertyId, @RequestBody Address address) {
        Property property = propertyService.addAddress(propertyId, address);
        return ResponseEntity.ok(property);
    }
    
    @PostMapping("/{propertyId}/images")
    public ResponseEntity<Property> addImages(@PathVariable Long propertyId, @RequestBody List<String> imageUrls) {
        Property property = propertyService.addImages(propertyId, imageUrls);
        return ResponseEntity.ok(property);
    }



    @GetMapping
    public ResponseEntity<List<Property>> getAllFiltered(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) PropertyType type,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) Furnish furnish,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        // If no filters are passed, return all properties
        boolean noFilters = city == null && area == null && type == null && gender == null
                && furnish == null && minPrice == null && maxPrice == null;

        if (noFilters) {
            return ResponseEntity.ok(propertyService.getAllProperties());
        }

        // Else return filtered properties
        List<Property> filtered = propertyService.getFilteredProperties(
                city, area, type, gender, furnish, minPrice, maxPrice
        );
        return ResponseEntity.ok(filtered);
    }
    
    @GetMapping("/paginated")
    public ResponseEntity<?> getFilteredWithPagination(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) PropertyType type,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) Furnish furnish,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Page<Property> resultPage = propertyService.getFilteredPropertiesPaged(
            city, area, type, gender, furnish, minPrice, maxPrice, page, size
        );

        Map<String, Object> response = new HashMap<>();
        response.put("properties", resultPage.getContent());
        response.put("currentPage", resultPage.getNumber());
        response.put("totalItems", resultPage.getTotalElements());
        response.put("totalPages", resultPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/available")
    public Property updateAvailability(@PathVariable Long id, @RequestParam boolean available) {
        return propertyService.updateAvailability(id, available);
    }
    
 
 // Endpoint: GET /api/properties/phone?phone=9876543210
    @GetMapping("/phone") // Fixed syntax: added quotation marks
    public ResponseEntity<List<Property>> getProperties(
            @RequestParam(required = false) String phone) {
        
        if (phone != null && !phone.trim().isEmpty()) {
            List<Property> ownerProperties = propertyService.getPropertiesByOwnerPhone(phone);
            return ResponseEntity.ok(ownerProperties);
        }
        
        // Default fallback if no parameter query string matches
        return ResponseEntity.ok(propertyService.getAllProperties());
    }
    
 // Endpoint: DELETE /api/properties/101?ownerId=5
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<?> deleteProperty(
            @PathVariable Long propertyId, 
            @RequestParam Long ownerId) {
        try {
            propertyService.deleteProperty(propertyId, ownerId);
            return ResponseEntity.ok("Property successfully deleted.");
        } catch (RuntimeException e) {
            // Returns a 400 Bad Request if ownership validation fails or id is missing
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
