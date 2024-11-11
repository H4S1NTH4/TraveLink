package com.example.demo.roomSeason;
import com.example.demo.contract.Contract;
import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.roomSeason.DTO.RoomSeasonSummaryDTO;
import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomTypeRepository;
import com.example.demo.season.Season;
import com.example.demo.season.SeasonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomSeasonService {

    private final RoomSeasonRepository roomSeasonRepository;
    private final RoomTypeRepository roomtypeRepository;
    private final SeasonRepository seasonRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomSeasonService(RoomSeasonRepository roomSeasonRepository, RoomTypeRepository roomtypeRepository, SeasonRepository seasonRepository, HotelRepository hotelRepository) {
        this.roomSeasonRepository = roomSeasonRepository;
        this.roomtypeRepository = roomtypeRepository;
        this.seasonRepository = seasonRepository;
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    public void addRoomTypesToSeason(Long roomTypeId, Long seasonId, RoomSeason roomSeasonData) {
        RoomType roomType = roomtypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new IllegalStateException("Room Type with id " + roomTypeId + " not found"));
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));

        Double price = roomSeasonData.getPrice();
        int quantity = roomSeasonData.getQuantity();
        RoomSeason roomSeason = new RoomSeason(roomType, season, price, quantity);
        roomSeason.setHotel(season.getContract().getHotel());
        roomSeasonRepository.save(roomSeason);
    }

    public List<RoomSeason> getRoomSeasons() {
        return roomSeasonRepository.findAll();
    }

    public RoomSeason getRoomSeasonById(Long roomSeasonId) {
        return roomSeasonRepository.findById(roomSeasonId)
                .orElseThrow(() -> new IllegalStateException("RoomSeason with id " + roomSeasonId + " not found"));

    }

    @Transactional
    public RoomSeason updateRoomSeason(Long roomSeasonId, RoomSeason roomSeasonData, Long roomTypeId, Long seasonId) {

       RoomSeason existingRoomSeason = roomSeasonRepository.findById(roomSeasonId)
               .orElseThrow(() -> new IllegalStateException("RoomSeason with id " + roomSeasonId + " not found"));

       if(roomTypeId != null) {
           RoomType roomType = roomtypeRepository.findById(roomTypeId)
                   .orElseThrow(() -> new IllegalStateException("Room Type with id " + roomTypeId + " not found"));

           existingRoomSeason.setRoomType(roomType);
       }
       if(seasonId != null) {
           Season season = seasonRepository.findById(seasonId)
                   .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));
           existingRoomSeason.setSeason(season);
       }

       existingRoomSeason.setPrice(roomSeasonData.getPrice());
       existingRoomSeason.setQuantity(roomSeasonData.getQuantity());

       RoomSeason updatedRoomSeason = roomSeasonRepository.save(existingRoomSeason);
        return updatedRoomSeason;
    }

    public void deleteRoomSeasonById(Long roomSeasonId) {
        if (!roomSeasonRepository.existsById(roomSeasonId)) {
            throw new IllegalStateException("RoomSeason with id " + roomSeasonId + " not found");
        }
        roomSeasonRepository.deleteById(roomSeasonId);
    }

    //get available room seasons when booking
    public List<RoomSeason> getRoomSeasonsBySeasonId(Long season_Id) {
         Season season = seasonRepository.findById(season_Id)
                    .orElseThrow(()-> new IllegalArgumentException("Season not found with Id: "+season_Id));

            List<RoomSeason> roomSeasons= roomSeasonRepository.findRoomSeasonsBySeasonId(season_Id);
            return roomSeasons;

    }

    public List<RoomSeasonSummaryDTO> findAvailableRoomSeasons(Long hotel_Id, LocalDate checkInDate, LocalDate checkOutDate, int guestCount) {
        System.out.println("guest count="+guestCount);
        Hotel hotel = hotelRepository.findById(hotel_Id)
                .orElseThrow(() -> new IllegalStateException("Hotel with id " + hotel_Id + " not found"));

        List<RoomSeason> roomSeasons= roomSeasonRepository.findAvailableRoomSeasons(hotel_Id, checkInDate, checkOutDate);

        //group room seasons by room type
        Map<Long, List<RoomSeason>> groupedByRoomType = roomSeasons.stream()
                .collect(Collectors.groupingBy(rs -> rs.getRoomType().getRoomTypeId()));

        //Iterate each room type & cal weighted avg price , min qty
        List<RoomSeasonSummaryDTO> summaryDTOS = groupedByRoomType.entrySet().stream()
                .map(entry ->{
                    List<RoomSeason> groupedSeasons = entry.getValue();

                    //calculate total days for entire period
                    long totalStayDays = ChronoUnit.DAYS.between(checkInDate,checkOutDate);
                    System.out.println("total staying dates = " +totalStayDays);

                    //calculate weighted avg price and markup
                    double totalWeightedPrice =0.0;
                    double totalWeightedMarkup=0.0;
                    long daysInSeasons = 0;

                    for(RoomSeason roomSeason : groupedSeasons){
                        Season season = roomSeason.getSeason();

                        System.out.println("season Id = " + season.getSeasonId());

                        // Calculate the overlap days between the booking dates and season dates
                        LocalDate seasonStart = season.getSeasonStartDate().isBefore(checkInDate) ? checkInDate : season.getSeasonStartDate();
                        //LocalDate seasonEnd = season.getSeasonEndDate().isAfter(checkOutDate) ? checkOutDate : season.getSeasonEndDate();
                        LocalDate seasonEnd = season.getSeasonEndDate().isBefore(checkOutDate) ? season.getSeasonEndDate().plusDays(1) : checkOutDate;

                        System.out.println("season start date = " +seasonStart);
                        System.out.println("season end date = " +seasonEnd);

                        long daysInSeason = ChronoUnit.DAYS.between(seasonStart, seasonEnd);
                        daysInSeasons += daysInSeason;
                        System.out.println("days in season = " +daysInSeason);

                        // Add to total weighted price
                        totalWeightedPrice += roomSeason.getPrice() * daysInSeason;
                        totalWeightedMarkup += season.getMarkup() * daysInSeason;
                    }
                    // Calculate the average price by dividing total weighted price by total days in seasons
                    double averagePrice = daysInSeasons > 0 ? totalWeightedPrice / daysInSeasons : 0.0;
                    double averageMarkup = daysInSeasons> 0 ? totalWeightedMarkup / daysInSeasons : 0.0;

                    // Get the minimum quantity from the grouped room seasons
                    int minQuantity = groupedSeasons.stream()
                            .mapToInt(RoomSeason::getQuantity)
                            .min()
                            .orElse(0);

                    // Collect room season IDs

                    List<Long> roomSeasonIds = groupedSeasons.stream()
                            .map(RoomSeason::getRoomSeasonId)
                            .collect(Collectors.toList());

                    // Get room type details
                    RoomType roomType = groupedSeasons.get(0).getRoomType();

                    RoomSeasonSummaryDTO summaryDTO = new RoomSeasonSummaryDTO();

                    summaryDTO.setRoomTypeId(roomType.getRoomTypeId());
                    summaryDTO.setRoomTypeName(roomType.getName());
                    summaryDTO.setDescription(roomType.getDescription());
                    summaryDTO.setCapacity(roomType.getCapacity());
                    summaryDTO.setAveragePrice(averagePrice);
                    summaryDTO.setMinQuantity(minQuantity);
                    summaryDTO.setRoomSeasonIds(roomSeasonIds);

                    //add markup to roomSeason objects (this is for temporarily )
                    summaryDTO.setMarkup(averageMarkup);

                    return summaryDTO;
                })
                .collect(Collectors.toList());

        return summaryDTOS;

    }
}