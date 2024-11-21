package com.example.demo.bookingSupplement;

import com.example.demo.bookingRoomType.BookingRoomType;
import com.example.demo.bookingRoomType.BookingRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookingSupplement")
public class BookingSupplementController {

        private final BookingSupplementService bookingSupplementService;

        @Autowired
        public BookingSupplementController(BookingSupplementService bookingSupplementService) {
            this.bookingSupplementService = bookingSupplementService;
        }


        @GetMapping
        public List<BookingSupplement> getBookingSupplements() {
            return bookingSupplementService.getBookingSupplements();
        }

        @PostMapping("/booking/{bookingId}")
        public ResponseEntity<List<BookingSupplement>> createBookingSupplement(
                @PathVariable Long bookingId,
                @RequestBody List<CreateBookingSupDTO> dto ){

            return bookingSupplementService.createBookingSupplement(bookingId,dto );

            /*
            req body :
            {
                "supplementQuantity" : 5,
                "noOfDays" : 3
            }
             */
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
