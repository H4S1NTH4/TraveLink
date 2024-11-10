package com.example.demo.hotel.dto;

public class HotelDTO {
    private Long hotel_Id;
    private String name;
    private String city;
    private String country;
    private Integer starRating;
    private String imageUrl;
    private String description;

    // Constructor
    public HotelDTO(Long hotelId, String name, String city, String country, Integer starRating, String imageUrl, String description) {
        this.hotel_Id = hotelId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.starRating = starRating;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    // Getters and Setters
    public Long getHotel_Id() {
        return hotel_Id;
    }

    public void setHotel_Id(Long hotel_Id) {
        this.hotel_Id = hotel_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
