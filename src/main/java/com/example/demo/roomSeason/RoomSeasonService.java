package com.example.demo.roomSeason;
import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomtypeRepository;
import com.example.demo.season.Season;
import com.example.demo.season.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomSeasonService {

    private final RoomSeasonRepository roomSeasonRepository;
    private final RoomtypeRepository roomtypeRepository;
    private final SeasonRepository seasonRepository;
    @Autowired
    public RoomSeasonService(RoomSeasonRepository roomSeasonRepository, RoomtypeRepository roomtypeRepository, SeasonRepository seasonRepository) {
        this.roomSeasonRepository =roomSeasonRepository;
        this.roomtypeRepository = roomtypeRepository;
        this.seasonRepository = seasonRepository;
    }

    public void addRoomTypesToSeason(Long roomTypeId, Long seasonId, double price, int quantity) {
        RoomType roomType = roomtypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new IllegalStateException("Room Type with id " + roomTypeId + " not found"));
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));

        RoomSeason roomSeason = new RoomSeason(roomType, season, price, quantity);
        roomSeasonRepository.save(roomSeason);
    }

    public List<RoomSeason> getRoomSeasons() {
        return roomSeasonRepository.findAll();
    }

    public RoomSeason getRoomSeasonById(Long roomSeasonId) {
            return roomSeasonRepository.findById(roomSeasonId)
                    .orElseThrow(()-> new IllegalStateException("RoomSeason with id "+roomSeasonId+" not found"));

        }


/*
    @Transactional
    public void addRoomTypesToSeason(Long roomTypeId, Long seasonId) {
        RoomType roomType = roomtypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new IllegalStateException("Room Type with id " + roomTypeId + " not found"));
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));

        roomType.addRoomSeason(season);
        season.addRoomSeason(roomType);
        roomtypeRepository.save(roomType);

    }
    */

}


