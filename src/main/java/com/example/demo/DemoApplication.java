package com.example.demo;

import com.example.demo.hotel.Hotel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@GetMapping
	public List<Hotel> hello(){
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
						"thisi policy"
				)
		);
	}


}