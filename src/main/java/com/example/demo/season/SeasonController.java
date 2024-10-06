package com.example.demo.season;

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

    @PostMapping
    public ResponseEntity<?> createSeason(@RequestBody Season season) {
        return ResponseEntity.ok(seasonService.createSeason(season));
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
