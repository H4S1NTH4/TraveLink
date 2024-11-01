package com.example.demo.roomSeason;

import com.example.demo.season.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomSeasonRepository extends JpaRepository<RoomSeason, Long> {

    @Query("SELECT rs FROM RoomSeason rs WHERE rs.season.seasonId = :season_Id")
    List<RoomSeason> findRoomSeasonsBySeasonId(@Param("season_Id") Long season_Id);


//    @Query("SELECT s FROM Season s WHERE s.contract.contract_Id = :contract_Id")
//    List<Season> findSeasonsByContractId(@Param("contract_Id") Long contract_Id);
}
