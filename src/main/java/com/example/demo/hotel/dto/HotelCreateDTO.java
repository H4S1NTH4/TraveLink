package com.example.demo.hotel.dto;

import jakarta.validation.constraints.*;

public class HotelCreateDTO {

    @NotNull(message = "Hotel name is required")
    @NotBlank(message = "Hotel name cannot be empty")
    private String name;

    @Email(message = "Please provide valid Email")
    private String email;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Address name is required")
    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number must be less than 15 characters")
    private String phone;

    @NotBlank(message = "URL is required")
    @Size(max = 255, message = "URL must be less than 255 characters")
    private String url;

    @NotBlank(message = "Image URL is required")
    @Size(max = 255, message = "URL must be less than 255 characters")
    private String imageUrl;

    @Min(value = 1, message = "Star rating must be between 1 and 7")
    private Integer starRating;

    private String policies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public @NotBlank(message = "Image URL is required") @Size(max = 255, message = "URL must be less than 255 characters") String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NotBlank(message = "Image URL is required") @Size(max = 255, message = "URL must be less than 255 characters") String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getPolicies() {
        return policies;
    }

    public void setPolicies(String policies) {
        this.policies = policies;
    }


}
