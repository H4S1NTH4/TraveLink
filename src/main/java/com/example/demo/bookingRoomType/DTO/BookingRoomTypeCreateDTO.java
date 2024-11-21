package com.example.demo.bookingRoomType.DTO;
import java.time.LocalDate;

public class BookingRoomTypeCreateDTO {

    private Long roomTypeId;  // Room type being booked
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private int quantity;
    private double roomPrice;   //from tbl
    private int guestCount;
    private Long roomSeasonId;
    private int numberOfDays;

    // Constructors, Getters, and Setters

    public BookingRoomTypeCreateDTO() {}

    public BookingRoomTypeCreateDTO(Long roomTypeId,Long roomSeasonId, LocalDate checkinDate,
                                    LocalDate checkoutDate, int quantity, double roomPrice, int guestCount ,int numberOfDays) {
        this.roomTypeId = roomTypeId;
        this.roomSeasonId = roomSeasonId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.quantity = quantity;
        this.roomPrice = roomPrice;
        this.guestCount = guestCount;
        this.numberOfDays = numberOfDays;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }
    public Long getRoomSeasonId() {
        return roomSeasonId;
    }

    public void setRoomSeasonId(Long roomSeasonId) {
        this.roomSeasonId = roomSeasonId;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}

