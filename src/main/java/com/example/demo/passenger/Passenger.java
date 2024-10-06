package com.example.demo.passenger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;
    private String name;
    private String email;
    private String phone;
    private String nic;

    public Passenger() {
    }

    public Passenger(Long passengerId, String name, String email, String phone, String nic) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nic = nic;
    }

    public Passenger(String name, String email, String phone, String nic) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nic = nic;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
