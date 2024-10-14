package com.example.demo.bookingRoomType;

import com.example.demo.booking.Booking;
import com.example.demo.booking.BookingRepository;
import com.example.demo.roomSeason.RoomSeason;
import com.example.demo.roomSeason.RoomSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRoomTypeService {

    private final BookingRoomTypeRepository bookingRoomTypeRepository;
    private final RoomSeasonRepository roomSeasonRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingRoomTypeService(BookingRoomTypeRepository bookingRoomTypeRepository, RoomSeasonRepository roomSeasonRepository, BookingRepository bookingRepository) {
        this.bookingRoomTypeRepository = bookingRoomTypeRepository;
        this.roomSeasonRepository = roomSeasonRepository;
        this.bookingRepository = bookingRepository;
    }



    public ResponseEntity<BookingRoomType> createBookingRoomType(Long bookingId, Long roomSeasonId, BookingRoomType bookingRoomType) {

        // room price from roomSeason , room type name form roomType tbl
        RoomSeason roomSeason = roomSeasonRepository.findById(roomSeasonId)
                .orElseThrow(() -> new IllegalStateException("RoomSeason with id " + roomSeasonId + " not found"));

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalStateException("Booking with id " + bookingId + " not found"));

        // Validate no of free rooms >= quantity

        //Double price = roomSeason.getPrice();
        //String roomType = roomSeason.getRoomType().getName();

        bookingRoomType.setRoomPrice(roomSeason.getPrice());
        bookingRoomType.setRoomTypeName(roomSeason.getRoomType().getName());
        bookingRoomType.setCapacity(roomSeason.getRoomType().getCapacity());
        bookingRoomType.setBooking(booking);
        bookingRoomType.setRoomSeason(roomSeason);

         bookingRoomTypeRepository.save(bookingRoomType);
        return ResponseEntity.ok(bookingRoomType);
    }

    public List<BookingRoomType> getBookingRoomTypes() {
       return bookingRoomTypeRepository.findAll();
    }
}
