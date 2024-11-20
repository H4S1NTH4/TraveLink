package com.example.demo.booking;

import com.example.demo.bookingRoomType.BookingRoomType;
import com.example.demo.bookingSupplement.BookingSupplement;
import com.example.demo.hotel.Hotel;
import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.mapping.Join;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private int guestCount;
    private LocalDate bookingDate;
    private float totalAmount;
    private float totalDiscount;
    private float paidAmount;
    private float balancePayment;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<BookingRoomType> bookingRoomTypes = new HashSet<>();

    @OneToMany(mappedBy = "booking")
    private Set<BookingSupplement> bookingSupplements = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "hotel_Id")
    @JsonIgnoreProperties("contracts")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


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

    public Set<BookingRoomType> getBookingRoomTypes() {
        return bookingRoomTypes;
    }

    public void setBookingRoomTypes(Set<BookingRoomType> bookingRoomTypes) {
        this.bookingRoomTypes = bookingRoomTypes;
    }

    public Set<BookingSupplement> getBookingSupplements() {
        return bookingSupplements;
    }

    public void setBookingSupplements(Set<BookingSupplement> bookingSupplements) {
        this.bookingSupplements = bookingSupplements;
    }
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public float getBalancePayment() { return balancePayment;    }

    public void setBalancePayment(float balancePayment) { this.balancePayment = balancePayment;   }

    public float getPaidAmount() {  return paidAmount; }

    public void setPaidAmount(float paidAmount) { this.paidAmount = paidAmount; }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
