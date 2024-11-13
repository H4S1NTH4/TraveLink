package com.example.demo.bookingRoomType;

import com.example.demo.booking.Booking;
import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.room_type.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking_room_type")
public class BookingRoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brt_Id;

    private String roomTypeName; //from roomType tbl
    private double roomPrice;   //from tbl
    private int quantity;
    private LocalDate checkinDate;
    private LocalDate checkOutDate;
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "room_type_id", nullable = false)
    @JsonIgnore
    private RoomType roomType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "room_season_id", nullable = false)
    @JsonIgnore
    private RoomSeason roomSeason;

    @ManyToOne
    @JoinColumn(name = "booking_Id", nullable = false)
    @JsonIgnore
    private Booking booking;

    public BookingRoomType() {
    }

    public BookingRoomType(int quantity, double roomPrice, LocalDate checkinDate, LocalDate checkoutDate, String roomTypeName,int capacity, RoomType roomType) {
        this.quantity = quantity;
        this.roomPrice = roomPrice;
        this.checkinDate = checkinDate;
        this.checkOutDate = checkoutDate;
        this.roomTypeName = roomTypeName;
        this.capacity = capacity;
        this.roomType = roomType;
    }

    public Long getBrt_Id() {
        return brt_Id;
    }

    public void setBrt_Id(Long id) {
        this.brt_Id = id;
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

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkoutDate) {
        this.checkOutDate = checkoutDate;
    }
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RoomSeason getRoomSeason() {
        return roomSeason;
    }

    public void setRoomSeason(RoomSeason roomSeason) {
        this.roomSeason = roomSeason;
    }
}