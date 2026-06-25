package com.project.property.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.property.enums.Furnish;
import com.project.property.enums.Gender;
import com.project.property.enums.PropertyType;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    private Double rent;

    private Double deposit;
    
    private String locationUrl;

    private boolean twoWheelerParking;

    private boolean fourWheelerParking;
    
    private Integer noOfVacancies;
    
    private Integer bhk;
    
    private boolean available;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Furnish furnishing;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    
    public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public boolean isTwoWheelerParking() {
        return twoWheelerParking;
    }

    public void setTwoWheelerParking(boolean twoWheelerParking) {
        this.twoWheelerParking = twoWheelerParking;
    }

    public boolean isFourWheelerParking() {
        return fourWheelerParking;
    }

    public void setFourWheelerParking(boolean fourWheelerParking) {
        this.fourWheelerParking = fourWheelerParking;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Furnish getFurnishing() {
        return furnishing;
    }

    public void setFurnishing(Furnish furnishing) {
        this.furnishing = furnishing;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getNoOfVacancies() {
		return noOfVacancies;
	}

	public void setNoOfVacancies(Integer noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}
	
	

	public Integer getBhk() {
		return bhk;
	}

	public void setBhk(Integer bhk) {
		this.bhk = bhk;
	}
	
    public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	

	// Constructors
    public Property() {
    }

    
    public Property(Long id, String name, PropertyType type, Double rent,Double deposit, String locationUrl, Integer noOfVacancies ,Integer bhk,
                    boolean twoWheelerParking, boolean fourWheelerParking, boolean available,Address address,
                    Gender gender, Furnish furnishing, LocalDateTime createdAt,
                    List<Image> images, Owner owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rent = rent;
        this.deposit=deposit;
        this.locationUrl = locationUrl;
        this.noOfVacancies=noOfVacancies;
        this.bhk=bhk;
        this.twoWheelerParking = twoWheelerParking;
        this.fourWheelerParking = fourWheelerParking;
        this.available=available;
        this.address = address;
        this.gender = gender;
        this.furnishing = furnishing;
        this.createdAt = createdAt;
        this.images = images;
        this.owner = owner;
    }
}
