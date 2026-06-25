package com.project.property.controller;

import com.project.property.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/s3")
// Best practice: Use a wildcard or your specific frontend URL
@CrossOrigin(origins = "http://localhost:5173") 
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @GetMapping("/generate-url")
    public ResponseEntity<Map<String, String>> getPresignedUrl(
            @RequestParam String fileName,
            @RequestParam String contentType) {
        
        // Ensure the filename is unique to avoid Youshelt users overwriting each other's photos
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

        // The service already returns a Map with "uploadUrl" and "publicUrl"
        Map<String, String> s3Data = s3Service.generatePresignedUrl(uniqueFileName, contentType);

        return ResponseEntity.ok(s3Data);
    }
}