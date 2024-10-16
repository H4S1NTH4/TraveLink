package com.example.demo.room_type;

import com.example.demo.season.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomtypeRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomtypeRepository, SeasonRepository seasonRepository) {
        this.roomtypeRepository = roomtypeRepository;
        this.seasonRepository = seasonRepository;
    }

    public List<RoomType> getRoomTypes() {
        return roomtypeRepository.findAll();
    }

    public RoomType getRoomTypeById(Long roomTypeId) {
        return roomtypeRepository.findById(roomTypeId)
                .orElseThrow(()-> new IllegalStateException("Season with id "+roomTypeId+" not found"));

    }

    public Object createRoomType(RoomType roomType) {
        return roomtypeRepository.save(roomType);
    }

    public RoomType updateRoomType(Long roomTypeId, RoomType roomTypeDetails) {
        RoomType roomType = roomtypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new IllegalStateException("Room type with id " + roomTypeId + " not found"));

        roomType.setName(roomTypeDetails.getName());
        roomType.setCapacity(roomTypeDetails.getCapacity());
        roomType.setDescription(roomTypeDetails.getDescription());

        return roomtypeRepository.save(roomType);
    }
    public void deleteRoomType(Long roomTypeId) {

        roomtypeRepository.deleteById(roomTypeId);
    }


}
