package com.example.demo.roomSeason.DTO;

import java.util.List;

public class RoomSeasonSummaryDTO {

    private Long roomTypeId;
    private String roomTypeName;
    private String description;
    private int capacity;
    private double averagePrice;
    private int minQuantity;
    private List<Long> roomSeasonIds;
    private double markup;

    public RoomSeasonSummaryDTO() {
    }

    public RoomSeasonSummaryDTO(Long roomTypeId, String roomTypeName, String description, int capacity, double averagePrice, int minQuantity, List<Long> roomSeasonIds) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.description = description;
        this.capacity = capacity;
        this.averagePrice = averagePrice;
        this.minQuantity = minQuantity;
        this.roomSeasonIds = roomSeasonIds;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public List<Long> getRoomSeasonIds() {
        return roomSeasonIds;
    }

    public void setRoomSeasonIds(List<Long> roomSeasonIds) {
        this.roomSeasonIds = roomSeasonIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public double getMarkup() {
        return markup;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }
}
