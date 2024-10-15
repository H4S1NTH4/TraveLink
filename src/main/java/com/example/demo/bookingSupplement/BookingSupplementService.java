package com.example.demo.bookingSupplement;

import com.example.demo.booking.Booking;
import com.example.demo.booking.BookingRepository;
import com.example.demo.supplementSeason.SupplementSeason;
import com.example.demo.supplementSeason.SupplementSeasonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingSupplementService {

    private final BookingSupplementRepository bookingSupplementRepository;
    private final SupplementSeasonRepository supplementSeasonRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingSupplementService(BookingSupplementRepository bookingSupplementRepository, SupplementSeasonRepository supplementSeasonRepository, BookingRepository bookingRepository) {
        this.bookingSupplementRepository = bookingSupplementRepository;
        this.supplementSeasonRepository = supplementSeasonRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<BookingSupplement> getBookingSupplements() {
        return bookingSupplementRepository.findAll();
    }

    @Transactional
    public ResponseEntity<BookingSupplement> createBookingSupplement(Long bookingId, Long supplementSeasonId, BookingSupplement bookingSupplement) {

        SupplementSeason supplementSeason = supplementSeasonRepository.findById(supplementSeasonId)
                .orElseThrow(() -> new IllegalStateException("SupplementSeason with id " + supplementSeasonId + "not found"));

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalStateException("Booking with id " + bookingId + "not found"));

        bookingSupplement.setBooking(booking);
        bookingSupplement.setSupplementName(supplementSeason.getSupplement().getSupplementName());
        bookingSupplement.setSupplementPrice(supplementSeason.getPrice());
        bookingSupplement.setSupplementSeason(supplementSeason);

        //no of nights & quantity will pass in the req body
        bookingSupplementRepository.save(bookingSupplement);
        return ResponseEntity.ok(bookingSupplement);
    }
}
