package com.example.demo.room_type;

import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.season.Season;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomTypeId;
    private String Name;
    private String Description;
    private int Capacity;
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<RoomSeason> roomSeasons = new HashSet<>();

    public RoomType() {

    }
    public RoomType(String name, Long roomTypeId, String description, int capacity) {
        Name = name;
        this.roomTypeId = roomTypeId;
        Description = description;
        Capacity = capacity;
    }

    public RoomType(int capacity, String name, String description) {
        Capacity = capacity;
        Name = name;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Set<RoomSeason> getRoomSeasons() {
        return roomSeasons;
    }

}
