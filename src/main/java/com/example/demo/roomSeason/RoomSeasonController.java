package com.example.demo.roomSeason;

import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomTypeController;
import com.example.demo.room_type.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/roomSeason")
public class RoomSeasonController {

    private final RoomSeasonService roomSeasonService;

    @Autowired
    public RoomSeasonController(RoomSeasonService roomSeasonService)
    {
        this.roomSeasonService =roomSeasonService;
    }

    @GetMapping
    public List<RoomSeason> getRoomSeasons() {
        return roomSeasonService.getRoomSeasons();
    }

    // Get a season by ID
    @GetMapping("{roomSeasonId}")
    public ResponseEntity<RoomSeason> getRoomSeasonById(@PathVariable Long roomSeasonId) {
        RoomSeason roomSeason= roomSeasonService.getRoomSeasonById(roomSeasonId);
        return ResponseEntity.ok(roomSeason);
    }

    @PostMapping("/{roomTypeId}/seasons/{seasonId}")
    public ResponseEntity<Void> addRoomTypesToSeason(
            @PathVariable Long roomTypeId,
            @PathVariable Long seasonId,
            @RequestBody Map<String, Object> requestBody) {

        // Extract price and quantity from the request body
        double price = (double) requestBody.get("price");
        int quantity = (int) requestBody.get("quantity");

        roomSeasonService.addRoomTypesToSeason(roomTypeId, seasonId, price, quantity);
        return ResponseEntity.ok().build();
    }


    /*
     @PutMapping("/{roomTypeId}/seasons/{season_Id}")
        public ResponseEntity<RoomType> addRoomTypesToSeason(@PathVariable Long roomTypeId, @PathVariable Long season_Id) {
          roomTypeService.addRoomTypesToSeason(roomTypeId, season_Id);
            return ResponseEntity.noContent().build();
        }
     */
}
