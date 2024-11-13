package com.example.demo.roomSeason;

import com.example.demo.roomSeason.DTO.RoomSeasonSummaryDTO;
import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomTypeController;
import com.example.demo.room_type.RoomTypeService;
import com.example.demo.season.Season;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    //Get all by seasonId
    @GetMapping("/bySeason/{seasonId}")
    public ResponseEntity<List<RoomSeason>> getRoomSeasonsBySeasonId(@PathVariable("seasonId") Long seasonId){
        List<RoomSeason> roomSeasons  = roomSeasonService.getRoomSeasonsBySeasonId(seasonId);
        return ResponseEntity.ok(roomSeasons);
    }
    @GetMapping("/availableByHotel/{hotel_Id}")
    public ResponseEntity<List<RoomSeasonSummaryDTO>> findAvailableRoomSeasons(@PathVariable Long hotel_Id,
                                                                               @RequestParam LocalDate checkInDate,
                                                                               @RequestParam LocalDate checkOutDate,
                                                                               @RequestParam int guestCount){
        List<RoomSeasonSummaryDTO> roomSeasons  = roomSeasonService.findAvailableRoomSeasons(hotel_Id,checkInDate,checkOutDate,guestCount) ;
        return ResponseEntity.ok(roomSeasons);

    }


    @PostMapping("/roomType/{roomTypeId}/seasons/{seasonId}")
    public ResponseEntity<Void> addRoomTypesToSeason(
            @PathVariable Long roomTypeId,
            @PathVariable Long seasonId,
            @RequestBody RoomSeason roomSeasonData) {

        roomSeasonService.addRoomTypesToSeason(roomTypeId, seasonId, roomSeasonData);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{roomSeasonId}")
    public ResponseEntity<RoomSeason> updateRoomSeason(
            @PathVariable Long roomSeasonId,
            @RequestBody RoomSeason roomSeason,
            @RequestParam(required = false) Long roomTypeId,
            @RequestParam(required = false) Long seasonId
            ) {

        RoomSeason updatedRoomSeason = roomSeasonService.updateRoomSeason(roomSeasonId,roomSeason,roomTypeId,seasonId);
        return ResponseEntity.ok(updatedRoomSeason);
    }
    @DeleteMapping("/{roomSeasonId}")
    public ResponseEntity<Void> deleteRoomSeason(@PathVariable Long roomSeasonId) {
        roomSeasonService.deleteRoomSeasonById(roomSeasonId);
        return ResponseEntity.noContent().build();
    }
    

}
