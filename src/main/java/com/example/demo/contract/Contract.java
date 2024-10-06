package com.example.demo.contract;

import com.example.demo.hotel.Hotel;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_Id;

    //Many Contracts to One Hotel mapping
    @ManyToOne
    @JoinColumn(name="hotel_Id", referencedColumnName = "hotel_Id")
    private Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public Hotel getHotel() {
        return hotel;
    }

    LocalDate startDate;
    LocalDate endDate;
    float prePaymentPercentage;
    int balancePaymentDay;
    int cancelationDeadline;
    float cancelationFeePercentage;
    String cancelationPolicy;
    String termsAndConditions;

    public Contract(){

    }

    public Contract(LocalDate startDate, LocalDate endDate, float prePaymentPercentage, int balancePaymentDay, int cancelationDeadline, float cancelationFeePercentage, String cancelationPolicy, String termsAndConditions, Long contract_Id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.prePaymentPercentage = prePaymentPercentage;
        this.balancePaymentDay = balancePaymentDay;
        this.cancelationDeadline = cancelationDeadline;
        this.cancelationFeePercentage = cancelationFeePercentage;
        this.cancelationPolicy = cancelationPolicy;
        this.termsAndConditions = termsAndConditions;
        this.contract_Id = contract_Id;
    }

    public Contract(LocalDate startDate, LocalDate endDate, float prePaymentPercentage, int balancePaymentDay, int cancelationDeadline, float cancelationFeePercentage, String cancelationPolicy, String termsAndConditions) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.prePaymentPercentage = prePaymentPercentage;
        this.balancePaymentDay = balancePaymentDay;
        this.cancelationDeadline = cancelationDeadline;
        this.cancelationFeePercentage = cancelationFeePercentage;
        this.cancelationPolicy = cancelationPolicy;
        this.termsAndConditions = termsAndConditions;
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

    public float getPrePaymentPercentage() {
        return prePaymentPercentage;
    }

    public void setPrePaymentPercentage(float prePaymentPercentage) {
        this.prePaymentPercentage = prePaymentPercentage;
    }

    public int getBalancePaymentDay() {
        return balancePaymentDay;
    }

    public void setBalancePaymentDay(int balancePaymentDay) {
        this.balancePaymentDay = balancePaymentDay;
    }

    public int getCancelationDeadline() {
        return cancelationDeadline;
    }

    public void setCancelationDeadline(int cancelationDeadline) {
        this.cancelationDeadline = cancelationDeadline;
    }

    public float getCancelationFeePercentage() {
        return cancelationFeePercentage;
    }

    public void setCancelationFeePercentage(float cancelationFeePercentage) {
        this.cancelationFeePercentage = cancelationFeePercentage;
    }

    public String getCancelationPolicy() {
        return cancelationPolicy;
    }

    public void setCancelationPolicy(String cancelationPolicy) {
        this.cancelationPolicy = cancelationPolicy;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public Long getContract_Id() {
        return contract_Id;
    }

    public void setContract_Id(Long contract_Id) {
        this.contract_Id = contract_Id;
    }
}
