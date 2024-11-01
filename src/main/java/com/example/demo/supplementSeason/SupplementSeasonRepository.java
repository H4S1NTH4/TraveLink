package com.example.demo.supplementSeason;

import com.example.demo.roomSeason.RoomSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplementSeasonRepository extends JpaRepository<SupplementSeason, Long> {

    @Query("SELECT s FROM SupplementSeason s WHERE s.season.seasonId = :season_Id")
    List<SupplementSeason> findSupplementSeasonsBySeasonId(@Param("season_Id") Long seasonId);


//    @Query("SELECT rs FROM RoomSeason rs WHERE rs.season.seasonId = :season_Id")
//    List<RoomSeason> findRoomSeasonsBySeasonId(@Param("season_Id") Long season_Id);
}
