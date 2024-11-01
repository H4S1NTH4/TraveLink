package com.example.demo.supplementSeason;

import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.season.Season;
import com.example.demo.season.SeasonRepository;
import com.example.demo.supplement.Supplement;
import com.example.demo.supplement.SupplementRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplementSeasonService {

    private final SupplementSeasonRepository supplementSeasonRepository;
    private final SupplementRepository supplementRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    public SupplementSeasonService(SupplementSeasonRepository supplementSeasonRepository, SupplementRepository supplementRepository, SeasonRepository seasonRepository) {
        this.supplementSeasonRepository = supplementSeasonRepository;
        this.supplementRepository = supplementRepository;
        this.seasonRepository = seasonRepository;
    }


    public List<SupplementSeason> getSupplementSeasons() {
        return supplementSeasonRepository.findAll();
    }
    public SupplementSeason getSupplementSeasonById(long supplementSeasonId) {
        SupplementSeason supplementSeason = supplementSeasonRepository.findById(supplementSeasonId)
                .orElseThrow(() -> new IllegalStateException("SupplementSeason with id " + supplementSeasonId + " not found"));
        return supplementSeason;
    }


    public SupplementSeason addSupplementToSeason(long supplementId, long seasonId, SupplementSeason supplementSeasonData) {
        Supplement supplement = supplementRepository.findById(supplementId)
                .orElseThrow(() -> new IllegalStateException("Supplement with id " + supplementId + " not found"));
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));

        Double price = supplementSeasonData.getPrice();

        SupplementSeason supplementSeason = new SupplementSeason(supplement, season, price);
        return supplementSeasonRepository.save(supplementSeason);
    }


    public SupplementSeason updateSupplementSeason(Long supplementSeasonId, SupplementSeason supplementSeasonData, Long supplementId, Long seasonId) {

        SupplementSeason existingSupplementSeason = supplementSeasonRepository.findById(supplementSeasonId)
                .orElseThrow(() -> new IllegalStateException("SupplementSeason with id " + supplementSeasonId + " not found"));

        if(supplementId != null){
            Supplement supplement = supplementRepository.findById(supplementId)
                    .orElseThrow(() -> new IllegalStateException("Supplement with id " + supplementId + " not found"));
            existingSupplementSeason.setSupplement(supplement);
        }
        if (seasonId != null) {
            Season season = seasonRepository.findById(seasonId)
                    .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));
            existingSupplementSeason.setSeason(season);
        }
        existingSupplementSeason.setPrice(supplementSeasonData.getPrice());

        return supplementSeasonRepository.save(existingSupplementSeason);

    }

    public void deleteSupplementSeason(Long supplementSeasonId) {
        if (!supplementSeasonRepository.existsById(supplementSeasonId)) {
            throw new IllegalStateException("SupplementSeason with id " + supplementSeasonId + " not found");
        }
        supplementSeasonRepository.deleteById(supplementSeasonId);
    }

    public List<SupplementSeason> getSupplementSeasonsBySeasonId(Long season_Id) {
        Season season = seasonRepository.findById(season_Id)
                .orElseThrow(()-> new IllegalArgumentException("Season not found with Id: "+season_Id));

        List<SupplementSeason> supplementSeasons= supplementSeasonRepository.findSupplementSeasonsBySeasonId(season_Id);
        return supplementSeasons;

    }
}
