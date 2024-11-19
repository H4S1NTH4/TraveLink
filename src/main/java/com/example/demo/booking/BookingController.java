package com.example.demo.booking;
import com.example.demo.booking.DTO.BookingRequestDTO;
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
    @PostMapping("/hotel/{hotelId}/user/{userId}")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO,
                                                 @PathVariable Long hotelId,
                                                 @PathVariable Long userId) {
        Booking booking = new Booking();
        booking.setGuestCount(bookingRequestDTO.getGuestCount());
        booking.setCheckInDate(bookingRequestDTO.getCheckInDate());
        booking.setCheckOutDate(bookingRequestDTO.getCheckOutDate());
        booking.setTotalAmount(bookingRequestDTO.getTotalAmount());
        booking.setTotalDiscount(bookingRequestDTO.getTotalDiscount());
        booking.setPaidAmount(bookingRequestDTO.getPaidAmount());
        booking.setBalancePayment(bookingRequestDTO.getBalancePayment());

        return ResponseEntity.ok(bookingService.createBooking(booking,hotelId,userId,bookingRequestDTO.getRoomTypeDTOs()));
    }
}
