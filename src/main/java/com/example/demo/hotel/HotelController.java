package com.example.demo.hotel;

import com.example.demo.hotel.dto.HotelCreateDTO;
import com.example.demo.hotel.dto.HotelUpdateRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getHotels(){
        return hotelService.getHotels();
    }

    @GetMapping("{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long hotelId){
        return hotelService.getHotelById(hotelId);
    }

    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam int guestCount,
                                    @RequestParam LocalDate checkInDate,
                                    @RequestParam LocalDate checkOutDate,
                                    @RequestParam(required = false) String location
                                    ){
        return hotelService.findAvailableHotels(guestCount, checkInDate,checkOutDate, location);
    }

    @PostMapping
    public ResponseEntity<?> registerNewHotel(@RequestBody @Valid HotelCreateDTO hotelCreateDTO) {
        // Call the service method and return the ResponseEntity
        return ResponseEntity.ok(hotelService.addNewHotel(hotelCreateDTO));

    }

    @DeleteMapping(path = "{hotel_Id}")
    public void deleteHotel(@PathVariable("hotel_Id") Long hotel_Id){
        hotelService.deleteHotel(hotel_Id);
    }

    @PutMapping(path ="{hotel_Id}")
    public ResponseEntity<?> updateHotel(@PathVariable("hotel_Id") Long hotel_Id,
                                         @RequestBody @Valid HotelUpdateRequest hotelUpdateRequest) {
        hotelService.updateHotel(hotel_Id, hotelUpdateRequest);
        return ResponseEntity.ok().build();
    }



} // end class

