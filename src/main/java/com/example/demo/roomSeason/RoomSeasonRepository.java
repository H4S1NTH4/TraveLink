package com.example.demo.roomSeason;

import com.example.demo.season.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomSeasonRepository extends JpaRepository<RoomSeason, Long> {

    @Query("SELECT rs FROM RoomSeason rs WHERE rs.season.seasonId = :season_Id")
    List<RoomSeason> findRoomSeasonsBySeasonId(@Param("season_Id") Long season_Id);

//  The error is this does't check whether the room type in in all covering seasons
//
//    @Query("""
//SELECT rs FROM RoomSeason rs
//JOIN rs.season s
//JOIN s.contract c
//JOIN c.hotel h
//WHERE h.hotel_Id = :hotel_Id
//AND s.seasonStartDate <= :checkOutDate
//AND s.seasonEndDate >= :checkInDate
//""")
//    List<RoomSeason>findAvailableRoomSeasons(
//            @Param("hotel_Id") Long hotel_Id,
//            @Param("checkInDate") LocalDate checkInDate,
//            @Param("checkOutDate") LocalDate checkOutDate  );

//SELECT rs,rs.roomType, MAX(rs.price) AS price, SUM(rs.quantity) AS totalQuantity

    @Query("""
SELECT rs
FROM RoomSeason rs
JOIN rs.season s
JOIN rs.roomType rt
JOIN s.contract c
JOIN c.hotel h
WHERE h.hotel_Id = :hotel_Id
AND s.seasonStartDate <= :checkOutDate
AND s.seasonEndDate >= :checkInDate
AND (
SELECT COUNT(rs2.RoomSeasonId)
FROM RoomSeason rs2
JOIN rs2.season s2
JOIN s2.contract c2
JOIN c2.hotel h2
WHERE h2.hotel_Id = :hotel_Id
AND rs2.roomType = rs.roomType
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
    List<RoomSeason> findAvailableRoomSeasons(
            @Param("hotel_Id") Long hotel_Id,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate);



    @Query("""
            SELECT SUM(brt.quantity)
            FROM BookingRoomType brt
            WHERE brt.booking.hotel.hotel_Id = :hotelId
            AND brt.roomType.roomTypeId = :roomTypeId
            AND brt.checkinDate < :checkOutDate
            AND brt.checkOutDate > :checkInDate
       """)
    int getBookedRoomQuantity(@Param("roomTypeId") Long roomTypeId,
                                     @Param("checkInDate") LocalDate checkInDate,
                                     @Param("checkOutDate") LocalDate checkOutDate,
                                     @Param("hotelId") Long hotelId);

    }