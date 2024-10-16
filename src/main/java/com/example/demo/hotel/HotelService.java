package com.example.demo.hotel;

import com.example.demo.hotel.dto.HotelCreateDTO;
import com.example.demo.hotel.dto.HotelUpdateRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service   // we can use @component annotation too.
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Autowired
    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @GetMapping
    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
        }

    public List<Hotel> findAvailableHotels(int guestCount, LocalDate checkInDate, LocalDate checkOutDate, String location) {
    return hotelRepository.findAvailableHotels(guestCount,checkInDate, checkOutDate,location);
    }

    public ResponseEntity<String> addNewHotel(HotelCreateDTO hotelCreateDTO) {
        if (hotelRepository.findHotelByEmail(hotelCreateDTO.getEmail()).isPresent()) {
            // Return HTTP 409 Conflict with an error message
            return ResponseEntity.status(409).body("Hotel with email " + hotelCreateDTO.getEmail() + " already exists.");
        }

        // using mapstruct for map DTO to entity
        Hotel hotel = hotelMapper.toHotel(hotelCreateDTO);
        hotelRepository.save(hotel);
        return ResponseEntity.status(201).body("Hotel created. id: "+hotel.getHotel_Id());
    }

    public void deleteHotel(Long hotel_Id) {
        boolean exists = hotelRepository.existsById(hotel_Id);
        if (!exists) {
            throw new IllegalStateException("Hotel with id " + hotel_Id + " does not exist") ;
        }
        hotelRepository.deleteById(hotel_Id);
    }

    @Transactional
    public void updateHotel(Long hotelId, HotelUpdateRequest hotelUpdateRequest) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalStateException("Hotel with id " + hotelId + " does not exist"));

        hotelMapper.updateHotelFromDto(hotelUpdateRequest, hotel);
        hotelRepository.save(hotel);


    } //end updateHotel()

    public ResponseEntity<Hotel> getHotelById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalStateException("Hotel with id " + hotelId + " does not exist"));
        return ResponseEntity.ok(hotel);
    }


}// end HotelService Class
