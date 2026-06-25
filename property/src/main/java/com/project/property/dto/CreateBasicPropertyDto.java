package com.project.property.dto;

import com.project.property.enums.Furnish;
import com.project.property.enums.Gender;
import com.project.property.enums.PropertyType;

public class CreateBasicPropertyDto {
    private String name;
    private PropertyType type;
    private Double rent;
    private Double deposit;
    private String locationUrl;
    private Boolean twoWheelerParking;
    private Boolean fourWheelerParking;
    private Integer noOfVacancies;
    private Integer bhk;
    private Gender gender;
    private Furnish furnishing;
    private Long ownerId;
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
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public CreateBasicPropertyDto(String name, PropertyType type, Double rent,Double deposit,String locationUrl,
			Boolean twoWheelerParking, Boolean fourWheelerParking, Integer noOfVacancies, Integer bhk, Gender gender,
			Furnish furnishing, Long ownerId) {
		super();
		this.name = name;
		this.type = type;
		this.rent = rent;
		this.deposit = deposit;
		this.locationUrl = locationUrl;
		this.twoWheelerParking = twoWheelerParking;
		this.fourWheelerParking = fourWheelerParking;
		this.noOfVacancies = noOfVacancies;
		this.bhk = bhk;
		this.gender = gender;
		this.furnishing = furnishing;
		this.ownerId = ownerId;
	}
	public CreateBasicPropertyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
