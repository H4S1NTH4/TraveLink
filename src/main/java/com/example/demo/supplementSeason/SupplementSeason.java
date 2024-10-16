package com.example.demo.supplementSeason;

import com.example.demo.bookingRoomType.BookingRoomType;
import com.example.demo.bookingSupplement.BookingSupplement;
import com.example.demo.room_type.RoomType;
import com.example.demo.season.Season;
import com.example.demo.supplement.Supplement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="supplement_season")
public class SupplementSeason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplementSeasonId;

    @ManyToOne
    @JoinColumn(name = "supplement_Id", nullable = false)
    private Supplement supplement;

    @ManyToOne
    @JoinColumn(name = "season_Id", nullable = false)
    @JsonIgnore
    private Season season;

    @OneToMany(mappedBy = "supplementSeason")
    @JsonIgnore
    private Set<BookingSupplement> bookingSupplements = new HashSet<>();

    private double price;

    public SupplementSeason() {
    }

    public SupplementSeason(Supplement supplement, Season season, double price) {
        this.supplement = supplement;
        this.season = season;
        this.price = price;
    }

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
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



    public Set<BookingSupplement> getBookingSupplements() {
        return bookingSupplements;
    }

    public void setBookingSupplements(Set<BookingSupplement> bookingSupplements) {
        this.bookingSupplements = bookingSupplements;
    }


}