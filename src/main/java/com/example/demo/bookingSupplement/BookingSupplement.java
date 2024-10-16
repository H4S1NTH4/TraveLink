package com.example.demo.bookingSupplement;

import com.example.demo.booking.Booking;
import com.example.demo.season.Season;
import com.example.demo.supplementSeason.SupplementSeason;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "booking_supplement")
public class BookingSupplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingSupplementId;

    private String supplementName;
    private Double supplementPrice;
    private int supplementQuantity;
    private int noOfDays;

    @ManyToOne
    @JoinColumn(name="supplement_season_Id", nullable = false)
    @JsonIgnore
    private SupplementSeason supplementSeason;

    @ManyToOne
    @JoinColumn(name = "booking_Id", nullable = false)
    @JsonIgnore
    private Booking booking;

    public BookingSupplement() {
    }

    public BookingSupplement(String supplementName, Double supplementPrice, int supplementQuantity, int noOfDays) {
        this.supplementName = supplementName;
        this.supplementPrice = supplementPrice;
        this.supplementQuantity = supplementQuantity;
        this.noOfDays = noOfDays;
    }

    public Long getBookingSupplementId() {
        return bookingSupplementId;
    }

    public void setBookingSupplementId(Long bookingSupplementId) {
        this.bookingSupplementId = bookingSupplementId;
    }

    public String getSupplementName() {
        return supplementName;
    }

    public void setSupplementName(String supplementName) {
        this.supplementName = supplementName;
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

    public SupplementSeason getSupplementSeason() {
        return supplementSeason;
    }

    public void setSupplementSeason(SupplementSeason supplementSeason) {
        this.supplementSeason = supplementSeason;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
