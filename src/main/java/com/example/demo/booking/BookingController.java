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
}

/*
package com.example.demo.room_type;






        // Get a season by ID
        @GetMapping("{roomTypeId}")
        public ResponseEntity<RoomType> getRoomTypeById(@PathVariable Long roomTypeId) {
            RoomType roomType = roomTypeService.getRoomTypeById(roomTypeId);
            return ResponseEntity.ok(roomType);
        }

        @PostMapping
        public ResponseEntity<?> createRoomType(@RequestBody RoomType roomType) {
            return ResponseEntity.ok(roomTypeService.createRoomType(roomType));
        }

        // Update a season
        @PutMapping("{roomTypeId}")
        public ResponseEntity<RoomType> updateSeason(@PathVariable Long roomTypeId, @RequestBody RoomType RoomTypeDetails) {
            RoomType updatedRoomType = roomTypeService.updateRoomType(roomTypeId, RoomTypeDetails);
            return ResponseEntity.ok(updatedRoomType);
        }

        // Delete a season
        @DeleteMapping("{roomTypeId}")
        public ResponseEntity<Void> deleteRoomType(@PathVariable Long roomTypeId) {
            roomTypeService.deleteRoomType(roomTypeId);
            return ResponseEntity.noContent().build();
        }




}

 */