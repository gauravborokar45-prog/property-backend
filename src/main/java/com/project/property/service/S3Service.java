package com.project.property.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class S3Service {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    /**
     * Generates a Pre-signed URL for uploading a file directly to S3.
     * @param fileName The name of the file (e.g., "room1.jpg")
     * @param contentType The MIME type (e.g., "image/jpeg")
     * @return A map containing the uploadUrl (for React) and publicUrl (for MySQL)
     */
    public Map<String, String> generatePresignedUrl(String fileName, String contentType) {
        
        // 1. Initialize the Presigner with your credentials
        S3Presigner presigner = S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        // 2. Define the object metadata
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();

        // 3. Create the Presign Request (valid for 10 minutes)
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(objectRequest)
                .build();

        // 4. Generate the URL
        String uploadUrl = presigner.presignPutObject(presignRequest).url().toString();
        
        // 5. Derive the Clean Public URL (Used to store in your 'images' table)
        // We strip the security tokens from the end of the URL
        String publicUrl = uploadUrl.split("\\?")[0];

        Map<String, String> response = new HashMap<>();
        response.put("uploadUrl", uploadUrl);
        response.put("publicUrl", publicUrl);

        // Always close the presigner to prevent memory leaks
        presigner.close();

        return response;
    }
}