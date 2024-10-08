package com.example.demo.season;

import com.example.demo.contract.Contract;
import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.room_type.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;
    @ManyToOne
    @JoinColumn(name="contract_Id", referencedColumnName = "contract_Id")
    @JsonIgnore
    private Contract contract;

    /*
        @ManyToMany(mappedBy = "roomSeasons")
        private Set<RoomType> seasonRoomTypes = new HashSet<>();

     */
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<RoomSeason> roomSeasons = new HashSet<>();

    private String seasonName;
    private LocalDate seasonStartDate;
    private LocalDate seasonEndDate;
    private float markup;

    public float getMarkup() {
        return markup;
    }

    public void setMarkup(float markup) {
        this.markup = markup;
    }

    public LocalDate getSeasonEndDate() {
        return seasonEndDate;
    }

    public void setSeasonEndDate(LocalDate seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    public LocalDate getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(LocalDate seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public void setContract(Contract contract){
        this.contract=contract;
    }
    public Contract getContract() {
        return contract;
    }
    public Set<RoomSeason> getRoomSeasons() {
        return roomSeasons;
    }


}
