package com.example.demo.roomSeason;
import com.example.demo.contract.Contract;
import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomTypeRepository;
import com.example.demo.season.Season;
import com.example.demo.season.SeasonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<RoomSeason> findAvailableRoomSeasons(Long hotel_Id, LocalDate checkInDate, LocalDate checkOutDate, int guestCount) {
        System.out.println("guest count="+guestCount);
        Hotel hotel = hotelRepository.findById(hotel_Id)
                .orElseThrow(() -> new IllegalStateException("Hotel with id " + hotel_Id + " not found"));

        List<RoomSeason> roomSeasons= roomSeasonRepository.findAvailableRoomSeasons(hotel_Id, checkInDate, checkOutDate);
        return roomSeasons;

    }
}