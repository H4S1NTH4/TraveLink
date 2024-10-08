package com.example.demo.season;

import com.example.demo.contract.Contract;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeasonValidator {

    public void validateStartAndEnd(Season newSeason){
        if(!newSeason.getSeasonStartDate().isBefore(newSeason.getSeasonEndDate())){
            throw new IllegalArgumentException("The new season start Date should less than the End date.");
        }

    }
    public void seasonWithinContratPeriod(Season newSeason, Contract contract){
        if( newSeason.getSeasonStartDate().isBefore(contract.getStartDate()) ||
                newSeason.getSeasonEndDate().isAfter(contract.getEndDate()) ){
            throw new IllegalArgumentException("Season dates must be within the contract period.");
        }
    }

    public void validateNoOverlap(Long contract_Id, Season newSeason, SeasonRepository seasonRepository) {
        List<Season> existingSeasons = seasonRepository.findSeasonsByContractId(contract_Id);

        for (Season existingSeason : existingSeasons) {
            if (isOverlapping(existingSeason, newSeason)) {
                throw new IllegalArgumentException("The new season overlaps with an existing season.");
            }
        }
    }

    //Helper method for the validateNoOverlap
        private boolean isOverlapping(Season existingSeason, Season newSeason) {
            return !(newSeason.getSeasonEndDate().isBefore(existingSeason.getSeasonStartDate()) ||
                    newSeason.getSeasonStartDate().isAfter(existingSeason.getSeasonEndDate()));
        }
}
