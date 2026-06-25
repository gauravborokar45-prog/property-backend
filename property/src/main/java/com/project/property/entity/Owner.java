package com.project.property.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    private String otp;

    private LocalDateTime otpGeneratedAt;

    // Added password field
    @Column(nullable = false)
    private String password; 

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Property> properties;

    // --- GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public LocalDateTime getOtpGeneratedAt() { return otpGeneratedAt; }
    public void setOtpGeneratedAt(LocalDateTime otpGeneratedAt) { this.otpGeneratedAt = otpGeneratedAt; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Property> getProperties() { return properties; }
    public void setProperties(List<Property> properties) { this.properties = properties; }

    // --- CONSTRUCTORS ---

    // Updated full constructor
    public Owner(Long id, String name, String email, String phone, String otp, LocalDateTime otpGeneratedAt,
            String password, List<Property> properties) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.otp = otp;
        this.otpGeneratedAt = otpGeneratedAt;
        this.password = password;
        this.properties = properties;
    }

    public Owner() {
        super();
    } 
}