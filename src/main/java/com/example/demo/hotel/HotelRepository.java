package com.example.demo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
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
    SELECT
        s.season_id,
        s.season_start_date,
        s.season_end_date,
        rs.hotel_id
    FROM
        Season s
    JOIN
        Room_Season rs ON s.season_id = rs.season_id
    WHERE
        s.season_start_date <= :checkOutDate AND
        s.season_end_date >= :checkInDate
),
BookedCapacity AS (
    SELECT
        sc.season_id,
        SUM(rt.capacity * brt.quantity) AS booked_capacity
    FROM
        SeasonsCovered sc
    JOIN
        Room_Season rs ON sc.season_id = rs.season_id
    JOIN
        Room_Type rt ON rs.room_type_id = rt.room_type_id
    JOIN
        Booking_Room_Type brt ON brt.room_season_id = rs.room_season_id
    WHERE
        brt.checkin_date < :checkOutDate
        AND brt.check_out_date > :checkInDate
    GROUP BY
        sc.season_id
),
AvailableCapacity AS (
    SELECT
        scp.hotelId,
        scp.season_id,
        scp.total_capacity,
        COALESCE(bc.booked_capacity, 0) AS booked_capacity,
        (scp.total_capacity - COALESCE(bc.booked_capacity, 0)) AS available_capacity
    FROM
        seasonal_capacity scp
    JOIN
        SeasonsCovered sc ON scp.season_id = sc.season_id
    LEFT JOIN
        BookedCapacity bc ON scp.season_id = bc.season_id
)
SELECT
    h.*
FROM
    Hotel h
WHERE
    EXISTS (
        SELECT 1
        FROM Room_Season rs
        JOIN Season s ON rs.season_id = s.season_id
        WHERE rs.hotel_id = h.hotel_id
          AND s.season_start_date <= :checkInDate
          AND s.season_end_date >= :checkInDate
    )
    AND EXISTS (
        SELECT 1
        FROM Room_Season rs
        JOIN Season s ON rs.season_id = s.season_id
        WHERE rs.hotel_id = h.hotel_id
          AND s.season_start_date <= :checkOutDate
          AND s.season_end_date >= :checkOutDate
    )
    AND (
        (:location IS NULL OR
         h.address LIKE CONCAT('%', :location, '%') OR
         h.city LIKE CONCAT('%', :location, '%') OR
         h.state LIKE CONCAT('%', :location, '%') OR
         h.country LIKE CONCAT('%', :location, '%'))
    )
    AND (
        SELECT MIN(ac.available_capacity)
        FROM AvailableCapacity ac
        WHERE ac.hotelId = h.hotel_id
    ) >= :guestCount -- Ensure all seasons for hotel have capacity >= guestCount
""", nativeQuery = true)
    List<Hotel> findAvailableHotels(
            @Param("guestCount") int guestCount,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("location") String location // location can be null
    );




}

/*
first query

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
 */



/*
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
WHERE (
          (:location IS NULL OR\s
          h.address LIKE CONCAT('%', :location, '%') OR\s
          h.city LIKE CONCAT('%', :location, '%') OR\s
          h.state LIKE CONCAT('%', :location, '%') OR\s
          h.country LIKE CONCAT('%', :location, '%'))
      )
AND h.hotel_id IN (SELECT hotel_id FROM AvailableHotels)
AND EXISTS(SELECT 1 FROM CheckInCovered)
AND EXISTS(SELECT 1 FROM CheckOutCovered)
AND NOT EXISTS (SELECT 1 FROM GapExists)
 """, nativeQuery = true)
    List<Hotel> findAvailableHotels(
            @Param("guestCount") int guestCount,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("location") String location //location can be null
    );
 */


/*
not working properly.
this doesnt exclude hotels if the checkout date is not covered by a season

 @Query(value = """
WITH SeasonsCovered AS (
    SELECT s.season_start_date, s.season_end_date, s.season_id
    FROM Season s
     WHERE s.season_start_date <= :checkOutDate AND s.season_end_date >= :checkInDate
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
CheckInCovered AS (
    SELECT 1
    FROM SeasonsCovered sc
    WHERE sc.season_start_date <= :checkInDate AND sc.season_end_date >= :checkInDate
),
CheckOutCovered AS (
    SELECT 1
    FROM SeasonsCovered sc
    WHERE sc.season_start_date <= :checkOutDate AND sc.season_end_date >= :checkOutDate
),
AvailableHotels AS (
    SELECT h.hotel_id
    FROM Hotel h
    JOIN room_season rs ON rs.hotel_id = h.hotel_id
    JOIN room_type rt ON rt.room_type_id = rs.room_type_id
    JOIN SeasonsCovered sc ON sc.season_id = rs.season_id
    -- Here we group by hotel_id and season_id
    GROUP BY h.hotel_id, sc.season_id
    HAVING SUM((rs.quantity - COALESCE((
               SELECT SUM(brt.quantity)
               FROM booking_room_type brt
               WHERE brt.room_season_id = rs.room_season_id
               AND brt.checkin_date < :checkOutDate  -- user checkout
               AND brt.checkout_date > :checkInDate  -- user checkin
           ), 0)) * rt.capacity ) >= :guestCount
    )

    -- having dala filter krnn sc eken eana season wlin aduma eka < guest capacity blnn

SELECT h.*
FROM Hotel h
WHERE (
          (:location IS NULL OR
          h.address LIKE CONCAT('%', :location, '%') OR
          h.city LIKE CONCAT('%', :location, '%') OR
          h.state LIKE CONCAT('%', :location, '%') OR
          h.country LIKE CONCAT('%', :location, '%'))
      )
AND EXISTS (SELECT 1 FROM CheckInCovered)
AND EXISTS (SELECT 1 FROM CheckOutCovered)
AND NOT EXISTS (SELECT 1 FROM GapExists)
AND h.hotel_id IN (SELECT hotel_id FROM AvailableHotels)
 """, nativeQuery = true)
    List<Hotel> findAvailableHotels(
            @Param("guestCount") int guestCount,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("location") String location //location can be null
    );

 */


/*
WITH SeasonsCovered AS (
         SELECT
             s.season_id,
             s.season_start_date,
             s.season_end_date
         FROM
             Season s
         WHERE
             s.season_start_date <= :checkOutDate AND
             s.season_end_date >= :checkInDate
     ),
     GapExists AS (
         SELECT
             1
         FROM
             SeasonsCovered sc1
         WHERE
             sc1.season_end_date < :checkOutDate
             AND NOT EXISTS (
                 SELECT
                     1
                 FROM
                     SeasonsCovered sc2
                 WHERE
                     sc2.season_start_date = sc1.season_end_date + INTERVAL 1 DAY
             )
     ),
     BookedCapacity AS (
         SELECT
             sc.season_id,
             SUM(rt.capacity * brt.quantity) AS booked_capacity
         FROM
             SeasonsCovered sc
         JOIN
             Room_Season rs ON sc.season_id = rs.season_id
         JOIN
             Room_Type rt ON rs.room_type_id = rt.room_type_id
         JOIN
             Booking_Room_Type brt ON brt.room_season_id = rs.room_season_id
         WHERE
             brt.checkin_date < :checkOutDate -- User checkout date
             AND brt.checkout_date > :checkInDate -- User checkin date
         GROUP BY
             sc.season_id
     ),
     AvailableCapacity AS (
         SELECT
             scp.hotelId,
             scp.season_id,
             scp.total_capacity,
             COALESCE(bc.booked_capacity, 0) AS booked_capacity,
             (scp.total_capacity - COALESCE(bc.booked_capacity, 0)) AS available_capacity
         FROM
             seasonal_capacity scp
         LEFT JOIN
             BookedCapacity bc ON scp.season_id = bc.season_id
     )
     SELECT
         h.*
     FROM
         AvailableCapacity ac
     JOIN
         Hotel h ON ac.hotelId = h.hotel_id
     WHERE
         (
                   (:location IS NULL OR
                   h.address LIKE CONCAT('%', :location, '%') OR
                   h.city LIKE CONCAT('%', :location, '%') OR
                   h.state LIKE CONCAT('%', :location, '%') OR
                   h.country LIKE CONCAT('%', :location, '%'))
               )
         AND ac.available_capacity >= :guestCount -- Check against guest count
         AND NOT EXISTS (SELECT 1 FROM GapExists); -- Ensure no gaps exist
 """, nativeQuery = true)
    List<Hotel> findAvailableHotels(
            @Param("guestCount") int guestCount,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("location") String location //location can be null
    );

 */