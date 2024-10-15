package com.example.demo.bookingRoomType;

import com.example.demo.roomSeason.RoomSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookingRoomType")
public class BookingRoomTypeController {
    
    
    private final BookingRoomTypeService bookingRoomTypeService;

    @Autowired
    public BookingRoomTypeController(BookingRoomTypeService bookingRoomTypeService) {
        this.bookingRoomTypeService = bookingRoomTypeService;
    }

    @GetMapping
    public List<BookingRoomType> getBookingRoomTypes() {
        return bookingRoomTypeService.getBookingRoomTypes();
    }
    
    @PostMapping("/booking/{bookingId}/roomSeason/{roomSeasonId}")
    public ResponseEntity<BookingRoomType> createBookingRoomType(@PathVariable Long bookingId,
                                                                 @PathVariable Long roomSeasonId,
                                                                 @RequestBody BookingRoomType bookingRoomType) {

        return bookingRoomTypeService.createBookingRoomType(bookingId, roomSeasonId, bookingRoomType);
    }


    /*
    //can change the booked room type or add extra rooms
    @PutMapping("/booking/{bookingRoomTypeId}")
    public  ResponseEntity<BookingRoomType> updateBookingRoomType(@PathVariable Long bookingRoomTypeId,
                                                                 @RequestParam Long roomSeasonId,
                                                                 @RequestBody BookingRoomType bookingRoomType) {
        return bookingRoomTypeService.updateBookingRoomType(bookingRoomTypeId,roomSeasonId,bookingRoomType);
    }

    */
    
    
    
    
}
