package com.example.demo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository
        extends JpaRepository<Hotel, Long> {

    //"Hotel" in the query is the Hotel class name. This isn't Sql this is jpql
    @Query("SELECT h FROM Hotel h WHERE h.email = ?1")
    Optional<Hotel> findHotelByEmail(String email);

}