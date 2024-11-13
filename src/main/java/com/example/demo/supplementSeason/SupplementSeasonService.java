package com.example.demo.supplementSeason;

import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.roomSeason.DTO.RoomSeasonSummaryDTO;
import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.room_type.RoomType;
import com.example.demo.season.Season;
import com.example.demo.season.SeasonRepository;
import com.example.demo.supplement.Supplement;
import com.example.demo.supplement.SupplementRepository;
import com.example.demo.supplementSeason.DTO.SupplementSeasonSummaryDTO;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SupplementSeasonService {

    private final SupplementSeasonRepository supplementSeasonRepository;
    private final SupplementRepository supplementRepository;
    private final SeasonRepository seasonRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public SupplementSeasonService(SupplementSeasonRepository supplementSeasonRepository, SupplementRepository supplementRepository, SeasonRepository seasonRepository, HotelRepository hotelRepository) {
        this.supplementSeasonRepository = supplementSeasonRepository;
        this.supplementRepository = supplementRepository;
        this.seasonRepository = seasonRepository;
        this.hotelRepository = hotelRepository;
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

    public List<SupplementSeasonSummaryDTO> findAvailableSupplementSeasons(Long hotel_Id, LocalDate checkInDate, LocalDate checkOutDate) {

        Hotel hotel = hotelRepository.findById(hotel_Id)
                .orElseThrow(() -> new IllegalStateException("Hotel with id " + hotel_Id + " not found"));

        List<SupplementSeason> supplementSeasons= supplementSeasonRepository.findAvailableSupplementSeasons(hotel_Id, checkInDate, checkOutDate);

        //group supplement seasons by supplement type
        Map<Long, List<SupplementSeason>> groupedBySupplement = supplementSeasons.stream()
                .collect(Collectors.groupingBy(rs -> rs.getSupplement().getSupplement_Id()));

        //Iterate each room type & cal weighted avg price , min qty
        List<SupplementSeasonSummaryDTO> summaryDTOS = groupedBySupplement.entrySet().stream()
                .map(entry ->{
                    List<SupplementSeason> groupedSeasons = entry.getValue();

                    //calculate total days for entire period
                    long totalStayDays = ChronoUnit.DAYS.between(checkInDate,checkOutDate);
                    System.out.println("total staying dates = " +totalStayDays);

                    //calculate weighted avg price and markup
                    double totalWeightedPrice =0.0;
                    long daysInSeasons = 0;

                    for(SupplementSeason supplementSeason : groupedSeasons){
                        Season season = supplementSeason.getSeason();

                        System.out.println("season Id = " + season.getSeasonId());

                        // Calculate the overlap days between the booking dates and season dates
                        LocalDate seasonStart = season.getSeasonStartDate().isBefore(checkInDate) ? checkInDate : season.getSeasonStartDate();
                        LocalDate seasonEnd = season.getSeasonEndDate().isBefore(checkOutDate) ? season.getSeasonEndDate().plusDays(1) : checkOutDate;

                        System.out.println("season start date = " +seasonStart);
                        System.out.println("season end date = " +seasonEnd);

                        long daysInSeason = ChronoUnit.DAYS.between(seasonStart, seasonEnd);
                        daysInSeasons += daysInSeason;
                        System.out.println("days in season = " +daysInSeason);

                        // Add to total weighted price
                        totalWeightedPrice += supplementSeason.getPrice() * daysInSeason;
                    }
                    // Calculate the average price by dividing total weighted price by total days in seasons
                    double averagePrice = daysInSeasons > 0 ? totalWeightedPrice / daysInSeasons : 0.0;

                    // Collect supplement season IDs
                    List<Long> supplementSeasonIds = groupedSeasons.stream()
                            .map(SupplementSeason::getSupplementSeasonId)
                            .collect(Collectors.toList());

                    // Get supplement details
                    Supplement supplement = groupedSeasons.get(0).getSupplement();

                    SupplementSeasonSummaryDTO summaryDTO = new SupplementSeasonSummaryDTO();

                    summaryDTO.setSupplementId(supplement.getSupplement_Id());
                    summaryDTO.setSupplementName(supplement.getSupplementName());
                    summaryDTO.setDescription(supplement.getSupplementDescription());
                    summaryDTO.setAveragePrice(averagePrice);
                    summaryDTO.setSupplementSeasonIds(supplementSeasonIds);

                    return summaryDTO;
                })
                .collect(Collectors.toList());

        return summaryDTOS;

    }
}
