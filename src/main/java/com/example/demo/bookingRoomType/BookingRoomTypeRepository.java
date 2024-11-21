package com.example.demo.bookingRoomType;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BookingRoomTypeRepository extends JpaRepository<BookingRoomType, Long> {

//    @Query(value = """
//SELECT CASE WHEN
//            (:requestedQuantity + COALESCE(SUM(brt.quantity), 0)) <= rs.quantity
//            THEN true ELSE false END
//            FROM RoomType rt
//            LEFT JOIN BookingRoomType brt ON brt.roomType.roomTypeId = rt.roomTypeId
//            JOIN RoomSeason rs ON rs.roomType.roomTypeId = rt.roomTypeId
//            AND brt.checkinDate < :checkoutDate
//            AND brt.checkOutDate > :checkinDate
//            WHERE rt.id = :roomTypeId """)
//    boolean isRoomAvailable(@Param("roomTypeId") Long roomTypeId,
//                            @Param("checkinDate") LocalDate checkinDate,
//                            @Param("checkoutDate") LocalDate checkoutDate,
//                            @Param("requestedQuantity") int requestedQuantity);
}

