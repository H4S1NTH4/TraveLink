package com.example.demo.supplementSeason.DTO;

import java.util.List;

public class SupplementSeasonSummaryDTO {

    private Long supplementId;
    private String supplementName;
    private String description;
    private double averagePrice;
    private List<Long> supplementSeasonIds;

    public SupplementSeasonSummaryDTO() {
    }

    public SupplementSeasonSummaryDTO(Long supplementId, String supplementName, String description, double averagePrice, List<Long> supplementSeasonIds) {
        this.supplementId = supplementId;
        this.supplementName = supplementName;
        this.description = description;
        this.averagePrice = averagePrice;
        this.supplementSeasonIds = supplementSeasonIds;
    }

    public Long getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(Long supplementId) {
        this.supplementId = supplementId;
    }

    public String getSupplementName() {
        return supplementName;
    }

    public void setSupplementName(String supplementName) {
        this.supplementName = supplementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public List<Long> getSupplementSeasonIds() {
        return supplementSeasonIds;
    }

    public void setSupplementSeasonIds(List<Long> supplementSeasonIds) {
        this.supplementSeasonIds = supplementSeasonIds;
    }
}


