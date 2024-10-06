package com.example.demo.room_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/roomtype")
public class RoomTypeController {

        private final RoomTypeService roomTypeService;

        //dependency injection
        @Autowired
        public RoomTypeController(RoomTypeService roomTypeService) {
            this.roomTypeService = roomTypeService;
        }

        @GetMapping
        public List<RoomType> getRoomTypes() {
            return roomTypeService.getRoomTypes();
        }

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
        public ResponseEntity<Void> deleteSeason(@PathVariable Long roomTypeId) {
            roomTypeService.deleteRoomType(roomTypeId);
            return ResponseEntity.noContent().build();
        }

}
