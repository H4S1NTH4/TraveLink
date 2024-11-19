package com.example.demo.booking.DTO;
import com.example.demo.bookingRoomType.DTO.BookingRoomTypeCreateDTO;

import java.time.LocalDate;
import java.util.List;

public class BookingRequestDTO {
        private int guestCount;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private float totalAmount;
        private float totalDiscount;
        private float paidAmount;
        private float balancePayment;
        private List<BookingRoomTypeCreateDTO> roomTypeDTOs;

    public BookingRequestDTO() {}

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(float totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public float getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(float paidAmount) {
        this.paidAmount = paidAmount;
    }

    public float getBalancePayment() {
        return balancePayment;
    }

    public void setBalancePayment(float balancePayment) {
        this.balancePayment = balancePayment;
    }

    public List<BookingRoomTypeCreateDTO> getRoomTypeDTOs() {
        return roomTypeDTOs;
    }

    public void setRoomTypeDTOs(List<BookingRoomTypeCreateDTO> roomTypeDTOs) {
        this.roomTypeDTOs = roomTypeDTOs;
    }
}
