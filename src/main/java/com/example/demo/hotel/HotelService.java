package com.example.demo.hotel;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service   // we can use @component annotation too.
public class HotelService {
        public List<Hotel> getHotels(){
            return List.of(
                    new Hotel(
                            1L,
                            "Hilton",
                            "hi@gmail.com",
                            "this is descrip",
                            "this is address",
                            "Colombo",
                            "Western",
                            "Strilanka",
                            "0705559052",
                            "thisis url",
                            5,
                            "thiis policy"
                    )
            );
        }
    }
