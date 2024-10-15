package com.example.demo.bookingSupplement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSupplementRepository extends JpaRepository<BookingSupplement, Long> {
}
