package com.example.demo.contract.dto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ContractUpdateDTO {

    LocalDate startDate;
    LocalDate endDate;

    @NotNull(message = "Pre payment percentage required.")
    float prePaymentPercentage;
    int balancePaymentDay;
    int cancelationDeadline;
    float cancelationFeePercentage;
    String cancelationPolicy;
    String termsAndConditions;

    private Long hotel_Id;

    public Long getHotel_Id() {
        return hotel_Id;
    }

    public void setHotel_Id(Long hotel_Id) {
        this.hotel_Id = hotel_Id;
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

    @NotNull(message = "Pre payment percentage required.")
    public float getPrePaymentPercentage() {
        return prePaymentPercentage;
    }

    public void setPrePaymentPercentage(@NotNull(message = "Pre payment percentage required.") float prePaymentPercentage) {
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
}

