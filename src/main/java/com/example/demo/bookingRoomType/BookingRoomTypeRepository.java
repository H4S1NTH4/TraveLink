package com.example.demo.bookingRoomType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRoomTypeRepository extends JpaRepository<BookingRoomType, Long> {



    @Query("""
         SELECT min(rs.quantity -COALESCE((
            SELECT SUM(brt.quantity)
            FROM BookingRoomType brt
            WHERE brt.booking.hotel.hotel_Id = :hotelId
            AND brt.roomType.roomTypeId = :roomTypeId
            AND brt.checkinDate < :checkOutDate
            AND brt.checkOutDate > :checkInDate),0) )
        FROM RoomSeason rs
        WHERE rs.hotel.hotel_Id = :hotelId
        AND rs.roomType.roomTypeId = :roomTypeId
        AND rs.season.seasonStartDate <= :checkOutDate
        AND rs.season.seasonEndDate >= :checkInDate
       """)
    Integer getAvailableRoomQuantity(@Param("roomTypeId") Long roomTypeId,
                                          @Param("checkInDate") LocalDate checkInDate,
                                          @Param("checkOutDate") LocalDate checkOutDate,
                                          @Param("hotelId") Long hotelId);

    //       HAVING MIN(rs.quantity - COALESCE(SUM(brt.quantity), 0)) >= :requestedRoomQuantity




}
