package com.example.demo.discount;

import com.example.demo.contract.Contract;
import com.example.demo.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    //Many Discount to One Contract mapping
    @ManyToOne
    @JoinColumn(name="contract_Id", referencedColumnName = "contract_Id")
    @JsonIgnore
    private Contract contract;

    private String discountName;
    private String discountDescription;
    private String type; //percentage or flat
    private double value;
    private double minBookingCost;
    private int daysPriorArrival;
    private LocalDate startDate;
    private LocalDate endDate;
    private int isActive;  // 0-inactive 1- active






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

    public Discount(String discountName, String discountDescription, String type, double value, double minBookingCost, int daysPriorArrival, LocalDate startDate, LocalDate endDate, int isActive) {
        this.discountName = discountName;
        this.discountDescription = discountDescription;
        this.type = type;
        this.value = value;
        this.minBookingCost = minBookingCost;
        this.daysPriorArrival = daysPriorArrival;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMinBookingCost() {
        return minBookingCost;
    }

    public void setMinBookingCost(double minBookingCost) {
        this.minBookingCost = minBookingCost;
    }

    public int getDaysPriorArrival() {
        return daysPriorArrival;
    }

    public void setDaysPriorArrival(int daysPriorArrival) {
        this.daysPriorArrival = daysPriorArrival;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
