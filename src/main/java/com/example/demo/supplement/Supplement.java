package com.example.demo.supplement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplement_Id;

    private String supplementName;
    private String supplementDescription;

    public Supplement() {
    }

    public Supplement(Long supplyId, String supplementName, String supplementDescription) {
        this.supplement_Id = supplyId;
        this.supplementName = supplementName;
        this.supplementDescription = supplementDescription;
    }

    public Supplement(String supplementName, String supplementDescription) {
        this.supplementName = supplementName;
        this.supplementDescription = supplementDescription;
    }

    public Long getSupplement_Id() {
        return supplement_Id;
    }

    public void setSupplement_Id(Long supplementId) {
        this.supplement_Id = supplementId;
    }

    public String getSupplementName() {
        return supplementName;
    }

    public void setSupplementName(String supplyName) {
        this.supplementName = supplyName;
    }

    public String getSupplementDescription() {
        return supplementDescription;
    }

    public void setSupplementDescription(String supplyDescription) {
        this.supplementDescription = supplyDescription;
    }
}
