package com.example.demo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository
        extends JpaRepository<Hotel, Long> {

    //"Hotel" in the query is the Hotel class name. This isn't Sql this is jpql
    @Query("SELECT h FROM Hotel h WHERE h.email = ?1")
    Optional<Hotel> findHotelByEmail(String email);


    //Get hotels with occupancy for the number of guests

    @Query(value = """
WITH SeasonsCovered AS (
    SELECT s.season_start_date, s.season_end_date
    FROM Season s
    WHERE (s.season_start_date <= :checkInDate AND s.season_end_date >= :checkInDate)
        OR (s.season_start_date <= :checkOutDate AND s.season_end_date >= :checkOutDate)
),
CheckInCovered AS (
    SELECT 1
    FROM Season s
    WHERE s.season_start_date <= :checkInDate AND s.season_end_date >= :checkInDate
),
CheckOutCovered AS (
    SELECT 1
    FROM Season s
    WHERE s.season_start_date <= :checkOutDate AND s.season_end_date >= :checkOutDate
),
GapExists AS (
    SELECT 1
    FROM SeasonsCovered sc1
    WHERE sc1.season_end_date < :checkOutDate
    AND NOT EXISTS (
        SELECT 1
        FROM SeasonsCovered sc2
        WHERE sc2.season_start_date = sc1.season_end_date + INTERVAL 1 DAY )

),
AvailableHotels AS (
    SELECT h.hotel_id
    FROM Hotel h
    JOIN room_season rs ON h.hotel_id = rs.hotel_id
    JOIN room_type rt ON rt.room_type_id = rs.room_type_id
    GROUP BY h.hotel_id
    HAVING SUM((rs.quantity - COALESCE((
               SELECT SUM(brt.quantity)
               FROM booking_room_type brt
               WHERE brt.room_season_id = rs.room_season_id
               AND brt.checkin_date < :checkOutDate  -- user checkout
               AND brt.checkout_date > :checkInDate  -- user checkin
           ), 0)) * rt.capacity ) >= :guestCount
)

SELECT h.*
FROM Hotel h
WHERE h.hotel_id IN (SELECT hotel_id FROM AvailableHotels)
AND EXISTS(SELECT 1 FROM CheckInCovered)
AND EXISTS(SELECT 1 FROM CheckOutCovered)
AND NOT EXISTS (SELECT 1 FROM GapExists)
 """, nativeQuery = true)
    List<Hotel> findAvailableHotels(
            @Param("guestCount") int guestCount,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

}