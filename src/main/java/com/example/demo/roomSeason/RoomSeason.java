package com.example.demo.roomSeason;

import com.example.demo.room_type.RoomType;
import com.example.demo.season.Season;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="room_season")
public class RoomSeason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoomSeasonId;
    @ManyToOne
    @JoinColumn(name = "roomTypeId", nullable = false)
    private RoomType roomType;
    @ManyToOne
    @JoinColumn(name = "season_Id", nullable = false)
    private Season season;

    private double price;
    private int quantity;

    public RoomSeason(){ }

    public RoomSeason(RoomType roomType, Season season, double price, int quantity) {
        this.roomType = roomType;
        this.season = season;
        this.price = price;
        this.quantity = quantity;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
