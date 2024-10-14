package com.example.demo.booking;

import com.example.demo.bookingRoomType.BookingRoomType;
import com.example.demo.roomSeason.RoomSeason;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    int guestCount;
    LocalDate bookingDate;
    float totalAmount;
    float totalDiscount;
    //float balancePayment;
    // float paidAmount;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<BookingRoomType> bookingRoomTypes = new HashSet<>();

    public Booking() {
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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

   /* public float getBalancePayment() { return balancePayment;    }

    public void setBalancePayment(float balancePayment) { this.balancePayment = balancePayment;   }

    public float getPaidAmount() {  return paidAmount; }

    public void setPaidAmount(float paidAmount) { this.paidAmount = paidAmount; }

    */

}
