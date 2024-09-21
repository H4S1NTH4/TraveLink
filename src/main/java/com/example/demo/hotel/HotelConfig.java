/*
package com.example.demo.hotel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.CommandLinePropertySource;

import java.util.List;

@Configuration
public class HotelConfig {

    CommandLineRunner commandLineRunner(
            HotelRepository repository){
        return args -> {
            Hotel hilton=new Hotel(
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
            );

                Hotel galadari=new Hotel(
                        "Galadhari",
                        "gala@gmail.com",
                        "gala this is descrip",
                        "this is address",
                        "Colombo",
                        "Western",
                        "Strilanka",
                        "0705559052",
                        "thisis url",
                        5,
                        "thiis policy"
                );

repository.saveAll(
        List.of(hilton,galadari)
);
        }; //return end
    }// repo end
} // end class
*/