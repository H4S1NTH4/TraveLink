package com.example.demo.hotel;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service   // we can use @component annotation too.
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping
    public List<Hotel> getHotels(){
           return hotelRepository.findAll();
        }
    public void addNewHotel(Hotel hotel) {
       Optional<Hotel> hotelByEmail =  hotelRepository.findHotelByEmail(hotel.getEmail());
       if (hotelByEmail.isPresent()) {
          throw new IllegalStateException("Hotel already exists") ;
       }
        hotelRepository.save(hotel);
       System.out.println(hotel);
    }

    public void deleteHotel(Long hotel_Id) {
        boolean exists = hotelRepository.existsById(hotel_Id);
        if (!exists) {
            throw new IllegalStateException("Hotel with id " + hotel_Id + " does not exist") ;
        }
        hotelRepository.deleteById(hotel_Id);
    }

    @Transactional
    public void updateHotel(Long hotelId,
                            String name,
                            String email) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalStateException(
                        "Hotel with id " + hotelId + " does not exist") );

        if (name != null && !name.isEmpty() && !Objects.equals(hotel.getName(), name)) {
            hotel.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(hotel.getEmail(), email) ) {
            Optional<Hotel> hotelOptional = hotelRepository.findHotelByEmail(email);
            if (hotelOptional.isPresent()) {
                throw new IllegalStateException("Email already taken") ;
            }
            hotel.setEmail(email);
        }
    }
}// end HotelService Class






 /*
 new Hotel
 (1L,
  "Hilton"
  "hi@gmail.com",

"this is description",
"this is address",
"Colombo",
 Western",
"Sri lanka",
"0705559052",
"this is url",
5,
"this is policy" )
  */