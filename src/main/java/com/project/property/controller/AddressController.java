package com.project.property.controller;

import com.project.property.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/areas")
    public List<String> getAreasByCity(
            @RequestParam String city,
            @RequestParam(required = false) String query
    ) {
        return addressService.getAreasByCity(city, query);
    }
}
