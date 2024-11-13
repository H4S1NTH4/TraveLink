package com.example.demo.supplementSeason;

import com.example.demo.roomSeason.RoomSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SupplementSeasonRepository extends JpaRepository<SupplementSeason, Long> {

    @Query("SELECT s FROM SupplementSeason s WHERE s.season.seasonId = :season_Id")
    List<SupplementSeason> findSupplementSeasonsBySeasonId(@Param("season_Id") Long seasonId);

    @Query("""
SELECT ss
FROM SupplementSeason ss
JOIN ss.season s
JOIN ss.supplement sup
JOIN s.contract c
JOIN c.hotel h
WHERE h.hotel_Id = :hotel_Id
AND s.seasonStartDate <= :checkOutDate
AND s.seasonEndDate >= :checkInDate
AND (
SELECT COUNT(ss2.supplementSeasonId)
FROM SupplementSeason ss2
JOIN ss2.season s2
JOIN s2.contract c2
JOIN c2.hotel h2
WHERE h2.hotel_Id = :hotel_Id
AND ss2.supplement = ss.supplement
AND s2.seasonStartDate <= :checkOutDate
AND s2.seasonEndDate >= :checkInDate
) = (
    SELECT COUNT(DISTINCT s3.seasonId)
    FROM Season s3
    JOIN s3.contract c3
    JOIN c3.hotel h3
    WHERE h3.hotel_Id = :hotel_Id
    AND s3.seasonStartDate <= :checkOutDate
    AND s3.seasonEndDate >= :checkInDate
)
""")
    List<SupplementSeason> findAvailableSupplementSeasons(
            @Param("hotel_Id") Long hotel_Id,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate);



}
