package com.example.demo.season;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/season")
public class SeasonController {

    private final SeasonService seasonService;

//dependency injection
    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @Transactional
    @GetMapping
    public List<Season> getSeasons() {
        return seasonService.getSeasons();
    }

    // Get a season by ID
    @GetMapping("{seasonId}")
    public ResponseEntity<Season> getSeasonById(@PathVariable Long seasonId) {
        Season season = seasonService.getSeasonById(seasonId);
        return ResponseEntity.ok(season);
    }

    @GetMapping("/byContract/{contract_Id}")
    public ResponseEntity<List<Season>> getSeasonsByContractId(@PathVariable("contract_Id") Long contract_Id){
        List<Season> seasons  = seasonService.getSeasonsByContractId(contract_Id);
        return ResponseEntity.ok(seasons);

    }
    @PostMapping
    public ResponseEntity<?> createSeason(@RequestBody Season season, @RequestParam Long contract_Id) {
        return ResponseEntity.ok(seasonService.createSeason(season,contract_Id));
    }

    // Update a season
    @PutMapping("{seasonId}")
    public ResponseEntity<Season> updateSeason(@PathVariable Long seasonId, @RequestBody Season seasonDetails) {
        Season updatedSeason = seasonService.updateSeason(seasonId, seasonDetails);
        return ResponseEntity.ok(updatedSeason);
    }

    // Delete a season
    @DeleteMapping("{seasonId}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long seasonId) {
        seasonService.deleteSeason(seasonId);
        return ResponseEntity.noContent().build();
    }






}
