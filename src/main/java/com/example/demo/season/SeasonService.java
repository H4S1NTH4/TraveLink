package com.example.demo.season;

import com.example.demo.contract.Contract;
import com.example.demo.contract.ContractRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final ContractRepository contractRepository;

    private final SeasonValidator seasonValidator; // Inject the SeasonValidator


    @Autowired
    public SeasonService(SeasonRepository seasonRepository, ContractRepository contractRepository, SeasonValidator seasonValidator) {
        this.seasonRepository = seasonRepository;
        this.contractRepository = contractRepository;
        this.seasonValidator =seasonValidator;
    }
    @Transactional
    @GetMapping
    public List<Season> getSeasons() {
        return seasonRepository.findAll();
    }

    public Season getSeasonById(Long seasonId) {
        return seasonRepository.findById(seasonId)
                .orElseThrow(()-> new IllegalStateException("Season with id "+seasonId+" not found"));
    }

    public List<Season> getSeasonsByContractId(Long contract_Id){
        Contract contract = contractRepository.findById(contract_Id)
                .orElseThrow(()-> new IllegalArgumentException("Contract not found with Id: "+contract_Id));

        List<Season> seasons= seasonRepository.findSeasonsByContractId(contract_Id);
        return seasons;
    }

    public Season createSeason(Season newSeason,Long contract_Id) {
        Contract contract = contractRepository.findById(contract_Id).
                orElseThrow(() -> new IllegalArgumentException("Contract not found"));

        //season validations
        seasonValidator.validateStartAndEnd(newSeason);
        seasonValidator.validateNoOverlap(contract_Id, newSeason, seasonRepository);
        seasonValidator.seasonWithinContratPeriod(newSeason,contract);

        // Set the contract in the season entity
        newSeason.setContract(contract);

        return seasonRepository.save(newSeason);
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
