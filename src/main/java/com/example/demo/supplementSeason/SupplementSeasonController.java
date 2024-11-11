package com.example.demo.supplementSeason;

import com.example.demo.roomSeason.DTO.RoomSeasonSummaryDTO;
import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.supplementSeason.DTO.SupplementSeasonSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/supplementSeason")
public class SupplementSeasonController {

    private final SupplementSeasonService supplementSeasonService;
    private final SupplementSeasonRepository supplementSeasonRepository;

    @Autowired
    public SupplementSeasonController(SupplementSeasonService supplementSeasonService, SupplementSeasonRepository supplementSeasonRepository) {
        this.supplementSeasonService = supplementSeasonService;
        this.supplementSeasonRepository = supplementSeasonRepository;
    }

    @GetMapping
    public List<SupplementSeason> getSupplementSeasons() {
        return supplementSeasonService.getSupplementSeasons();
    }

    @GetMapping("{supplementSeasonId}")
    public ResponseEntity<SupplementSeason> getsupplementSeasonById(@PathVariable long supplementSeasonId) {
        SupplementSeason supplementSeason  = supplementSeasonService.getSupplementSeasonById(supplementSeasonId);
        return ResponseEntity.ok().body(supplementSeason);
    }

    //Get all by seasonId
    @GetMapping("/bySeason/{seasonId}")
    public ResponseEntity<List<SupplementSeason>> getSupplementSeasonsBySeasonId(@PathVariable("seasonId") Long seasonId){
        List<SupplementSeason> supplementSeasons  = supplementSeasonService.getSupplementSeasonsBySeasonId(seasonId);
        return ResponseEntity.ok(supplementSeasons);

    }
    @GetMapping("/availableByHotel/{hotel_Id}")
    public ResponseEntity<List<SupplementSeasonSummaryDTO>> findAvailableRoomSeasons(@PathVariable Long hotel_Id,
                                                                               @RequestParam LocalDate checkInDate,
                                                                               @RequestParam LocalDate checkOutDate){
        List<SupplementSeasonSummaryDTO> supplementSeasons  = supplementSeasonService.findAvailableSupplementSeasons(hotel_Id,checkInDate,checkOutDate) ;
        return ResponseEntity.ok(supplementSeasons);

    }

    @PostMapping("/supplement/{supplementId}/season/{seasonId}")
    public ResponseEntity<SupplementSeason> addSupplementToSeason(
            @PathVariable long supplementId,
            @PathVariable long seasonId,
            @RequestBody SupplementSeason supplementSeasonData) {
        return ResponseEntity.ok(supplementSeasonService.addSupplementToSeason(supplementId,seasonId,supplementSeasonData));
    }

    @PutMapping("/{supplementSeasonId}")
    public ResponseEntity<SupplementSeason> updateSupplementSeason(
            @PathVariable Long supplementSeasonId,
            @RequestBody SupplementSeason supplementSeasonData,
            @RequestParam(required = false) Long supplementId,
            @RequestParam(required = false) Long seasonId ){

        SupplementSeason updatedSupplementSeason = supplementSeasonService.updateSupplementSeason(supplementSeasonId,supplementSeasonData,supplementId,seasonId);
        return ResponseEntity.ok(updatedSupplementSeason);

    }

    @DeleteMapping("/{supplementSeasonId}")
    public ResponseEntity<Void> deleteSupplementSeason(@PathVariable Long supplementSeasonId) {
        supplementSeasonService.deleteSupplementSeason(supplementSeasonId);
        return ResponseEntity.noContent().build();
    }


    ///
}
