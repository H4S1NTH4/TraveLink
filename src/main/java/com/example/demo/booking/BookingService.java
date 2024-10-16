package com.example.demo.booking;

import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.season.Season;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, HotelRepository hotelRepository){
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }


    //create method createBooking
    public Booking createBooking(Booking booking, Long hotelId) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new IllegalArgumentException("Hotel with id: " +hotelId+ " not found"));

        //add logic to validate whether the rooms are available or not.
        booking.setHotel(hotel);
        return bookingRepository.save(booking);
    }


}