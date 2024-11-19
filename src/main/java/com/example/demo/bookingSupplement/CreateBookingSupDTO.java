package com.example.demo.bookingSupplement;

public class CreateBookingSupDTO {

    private Long supplementId;
    private Double supplementPrice;
    private int supplementQuantity;
    private int noOfDays;


    public CreateBookingSupDTO(Long supplementId, Double supplementPrice, int supplementQuantity, int noOfDays) {
        this.supplementId = supplementId;
        this.supplementPrice = supplementPrice;
        this.supplementQuantity = supplementQuantity;
        this.noOfDays = noOfDays;
    }

    public Long getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(Long supplementId) {
        this.supplementId = supplementId;
    }

    public Double getSupplementPrice() {
        return supplementPrice;
    }

    public void setSupplementPrice(Double supplementPrice) {
        this.supplementPrice = supplementPrice;
    }

    public int getSupplementQuantity() {
        return supplementQuantity;
    }

    public void setSupplementQuantity(int supplementQuantity) {
        this.supplementQuantity = supplementQuantity;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }
}
