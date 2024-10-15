package com.example.demo.bookingRoomType;

import com.example.demo.booking.Booking;
import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.room_type.RoomType;
import com.example.demo.season.Season;
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
    private double roomPrice;   //from roomSeason tbl
    private int quantity;
    private int guestCount;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "room_season_Id", nullable = false)
    @JsonIgnore
    private RoomSeason roomSeason;

    @ManyToOne
    @JoinColumn(name = "booking_Id", nullable = false)
    @JsonIgnore
    private Booking booking;

    public BookingRoomType() {
    }

    public BookingRoomType(int quantity, double roomPrice,int guestCount, LocalDate checkinDate, LocalDate checkoutDate, String roomTypeName) {
        this.quantity = quantity;
        this.roomPrice = roomPrice;
        this.guestCount = guestCount;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.roomTypeName = roomTypeName;
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
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }
    public int getGuestCount() {
        return guestCount;
    }
    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
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
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public RoomSeason getRoomSeason() {
        return roomSeason;
    }

    public void setRoomSeason(RoomSeason roomSeason) {
        this.roomSeason = roomSeason;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}