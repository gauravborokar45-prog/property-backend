package com.project.property.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.property.entity.Address;
import com.project.property.enums.Furnish;
import com.project.property.enums.Gender;
import com.project.property.enums.PropertyType;

public class CreatePropertyDto {

    private String name;
    private PropertyType type;
    private Double rent;
    private Double deposit;
    private String locationUrl;
    private Gender gender;
    private Boolean available;
    private Furnish furnishing;
    private Address address;
    private List<String> imageUrls = new ArrayList<>();
    private Long ownerId;
    private Boolean twoWheelerParking;
    private Boolean fourWheelerParking;
    private Integer noOfVacancies;
    private Integer bhk;

    public CreatePropertyDto() {
    }

    public CreatePropertyDto(String name, PropertyType type, Double rent,Double deposit, String locationUrl, Gender gender,
                             Furnish furnishing, Address address, List<String> imageUrls, Long ownerId,
                             Boolean twoWheelerParking, Boolean fourWheelerParking,Boolean available, Integer noOfVacancies, Integer bhk) {
        this.name = name;
        this.type = type;
        this.rent = rent;
        this.deposit=deposit;
        this.locationUrl = locationUrl;
        this.gender = gender;
        this.furnishing = furnishing;
        this.address = address;
        this.imageUrls = imageUrls;
        this.ownerId = ownerId;
        this.twoWheelerParking = twoWheelerParking;
        this.fourWheelerParking = fourWheelerParking;
        this.available=available;
        this.noOfVacancies = noOfVacancies;
        this.bhk = bhk;
    }

    // Getters and setters
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getTwoWheelerParking() {
        return twoWheelerParking;
    }

    public void setTwoWheelerParking(Boolean twoWheelerParking) {
        this.twoWheelerParking = twoWheelerParking;
    }

    public Boolean getFourWheelerParking() {
        return fourWheelerParking;
    }

    public void setFourWheelerParking(Boolean fourWheelerParking) {
        this.fourWheelerParking = fourWheelerParking;
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

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
