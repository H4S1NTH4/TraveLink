package com.example.demo.hotel;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void registerNewHotel(@RequestBody Hotel hotel){
        hotelService.addNewHotel(hotel);
    }

    @DeleteMapping(path = "{hotel_Id}")
    public void deleteHotel(@PathVariable("hotel_Id") Long hotel_Id){
        hotelService.deleteHotel(hotel_Id);
    }

    @PutMapping(path ="{hotel_Id}")
    public void updateHotel(
            @PathVariable("hotel_Id") Long hotel_Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        hotelService.updateHotel(hotel_Id, name, email);

    }



} // end class

