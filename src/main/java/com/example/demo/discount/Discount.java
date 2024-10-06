package com.example.demo.discount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;
    private String discountName;
    private String discountDescription;

    public Discount() {
    }

    public Discount(Long discountId, String discountName, String discountDescription) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountDescription = discountDescription;
    }

    public Discount(String discountName, String discountDescription) {
        this.discountName = discountName;
        this.discountDescription = discountDescription;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }
}
