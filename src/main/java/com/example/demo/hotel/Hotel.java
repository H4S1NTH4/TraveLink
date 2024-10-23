package com.example.demo.hotel;

import com.example.demo.contract.Contract;
import com.example.demo.roomSeason.RoomSeason;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotel_Id;

    private String name;
    private String email;
    private String description;
    private String address;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String url;
    private String imageUrl;
    private int starRating;
    private String policies;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<Contract> contracts = new HashSet<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<RoomSeason> roomSeasons = new HashSet<>();

    public Hotel() {
    }

    public Hotel(long hotel_Id,
                 String name,
                 String email,
                 String description,
                 String address,
                 String city,
                 String state,
                 String country,
                 String phone,
                 String url,
                 String imageUrl,
                 int starRating,
                 String policies) {
        this.hotel_Id = hotel_Id;
        this.name = name;
        this.email = email;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.url = url;
        this.imageUrl = imageUrl;
        this.starRating = starRating;
        this.policies = policies;
    }

    public Hotel(String name,
                 String email,
                 String description,
                 String address,
                 String city,
                 String state,
                 String country,
                 String phone,
                 String url,
                 String imageUrl,
                 int starRating,
                 String policies) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.url = url;
        this.imageUrl = imageUrl;
        this.starRating = starRating;
        this.policies = policies;
    }

    public long getHotel_Id() {
        return hotel_Id;
    }

    public void setHotel_Id(long id) {
        this.hotel_Id = id;
    }

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

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getPolicies() {
        return policies;
    }

    public void setPolicies(String policies) {
        this.policies = policies;
    }
    public Set<RoomSeason> getRoomSeasons() {
        return roomSeasons;
    }

    public void setRoomSeasons(Set<RoomSeason> roomSeasons) {
        this.roomSeasons = roomSeasons;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }


    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + hotel_Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", url='" + url + '\'' +
                ", starRating=" + starRating +
                ", policies='" + policies + '\'' +
                '}';
    }
}
