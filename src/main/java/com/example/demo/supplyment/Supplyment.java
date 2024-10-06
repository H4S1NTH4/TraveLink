package com.example.demo.supplyment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supplyment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplymentId;

    private String supplymentName;
    private String supplymentDescription;

    public Supplyment() {
    }

    public Supplyment(Long supplyId, String supplymentName, String supplymentDescription) {
        this.supplymentId = supplyId;
        this.supplymentName = supplymentName;
        this.supplymentDescription = supplymentDescription;
    }

    public Supplyment(String supplymentName, String supplymentDescription) {
        this.supplymentName = supplymentName;
        this.supplymentDescription = supplymentDescription;
    }

    public Long getSupplymentId() {
        return supplymentId;
    }

    public void setSupplymentId(Long supplymentId) {
        this.supplymentId = supplymentId;
    }

    public String getSupplymentName() {
        return supplymentName;
    }

    public void setSupplymentName(String supplyName) {
        this.supplymentName = supplyName;
    }

    public String getSupplymentDescription() {
        return supplymentDescription;
    }

    public void setSupplymentDescription(String supplyDescription) {
        this.supplymentDescription = supplyDescription;
    }
}
