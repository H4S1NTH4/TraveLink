package com.example.demo.booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/booking")
public class BookingController {
    private final  BookingService bookingService;

    //dependency injection
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookingService.getBookings();
    }

    //createBooking
    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking,
                                                 @PathVariable Long hotelId) {
        return ResponseEntity.ok(bookingService.createBooking(booking,hotelId));
    }
}
