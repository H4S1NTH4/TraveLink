package com.example.demo.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public List<Season> getSeasons() {
        return seasonRepository.findAll();
    }

    public Season getSeasonById(Long seasonId) {
        return seasonRepository.findById(seasonId)
                .orElseThrow(()-> new IllegalStateException("Season with id "+seasonId+" not found"));
    }

    public Season createSeason(Season season) {
        return seasonRepository.save(season);
    }

    public Season updateSeason(Long seasonId, Season seasonDetails) {
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));

        season.setSeasonName(seasonDetails.getSeasonName());
        season.setSeasonStartDate(seasonDetails.getSeasonStartDate());
        season.setSeasonEndDate(seasonDetails.getSeasonEndDate());
        season.setMarkup(seasonDetails.getMarkup());
        return seasonRepository.save(season);

    }
    public void deleteSeason(Long seasonId) {
        seasonRepository.deleteById(seasonId);
    }
}
