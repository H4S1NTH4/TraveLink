package com.example.demo.bookingSupplement;

import com.example.demo.booking.Booking;
import com.example.demo.booking.BookingRepository;
import com.example.demo.supplement.Supplement;
import com.example.demo.supplement.SupplementRepository;
import com.example.demo.supplementSeason.SupplementSeason;
import com.example.demo.supplementSeason.SupplementSeasonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingSupplementService {

    private final BookingSupplementRepository bookingSupplementRepository;
    private final SupplementSeasonRepository supplementSeasonRepository;
    private final BookingRepository bookingRepository;
    private final SupplementRepository supplementRepository;

    @Autowired
    public BookingSupplementService(BookingSupplementRepository bookingSupplementRepository, SupplementSeasonRepository supplementSeasonRepository, BookingRepository bookingRepository, SupplementRepository supplementRepository) {
        this.bookingSupplementRepository = bookingSupplementRepository;
        this.supplementSeasonRepository = supplementSeasonRepository;
        this.bookingRepository = bookingRepository;
        this.supplementRepository = supplementRepository;
    }

    public List<BookingSupplement> getBookingSupplements() {
        return bookingSupplementRepository.findAll();
    }

    @Transactional
    public ResponseEntity<List<BookingSupplement>> createBookingSupplement(Long bookingId, List<CreateBookingSupDTO> dtos) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalStateException("Booking with id " + bookingId + "not found"));

        List<BookingSupplement> bookingSupplements = new ArrayList<>();

        for(CreateBookingSupDTO dto : dtos) {

            BookingSupplement bookingSupplement = new BookingSupplement();

            Supplement supplement = supplementRepository.findById(dto.getSupplementId())
                    .orElseThrow(() -> new IllegalStateException("Supplement with id " + dto.getSupplementId() + "not found"));


            bookingSupplement.setBooking(booking);
            bookingSupplement.setSupplement(supplement);

            bookingSupplement.setSupplementName(supplement.getSupplementName());

            bookingSupplement.setSupplementPrice(dto.getSupplementPrice());
            bookingSupplement.setSupplementQuantity(dto.getSupplementQuantity());
            bookingSupplement.setNoOfDays(dto.getNoOfDays());

            bookingSupplements.add(bookingSupplementRepository.save(bookingSupplement));
        }

        return ResponseEntity.ok(bookingSupplements);
    }
}
