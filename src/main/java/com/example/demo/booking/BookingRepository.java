package com.example.demo.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    @Query("""
    Select b
    FROM Booking b
    WHERE b.user.userId = :user_Id""")
    List<Booking> findBookingsByUserId(@Param("user_Id") Long user_Id);
}
