package com.example.demo.booking;

import com.example.demo.bookingRoomType.BookingRoomType;
import com.example.demo.bookingRoomType.BookingRoomTypeRepository;
import com.example.demo.bookingRoomType.DTO.BookingRoomTypeCreateDTO;
import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomTypeRepository;
import com.example.demo.season.Season;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final BookingRoomTypeRepository bookingRoomTypeRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, HotelRepository hotelRepository, UserRepository userRepository, RoomTypeRepository roomTypeRepository, BookingRoomTypeRepository bookingRoomTypeRepository) {
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.bookingRoomTypeRepository = bookingRoomTypeRepository;
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }


    //create method createBooking
    public Booking createBooking(Booking booking, Long hotelId, Long userId, List<BookingRoomTypeCreateDTO> roomTypeDTOs) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel with id: " + hotelId + " not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id: " + userId + " not found"));

        booking.setHotel(hotel);
        booking.setUser(user);

        // Step 2: Save Booking
        Booking savedBooking = bookingRepository.save(booking);

        List<BookingRoomType> bookingRoomTypes = new ArrayList<>();
        for (BookingRoomTypeCreateDTO dto : roomTypeDTOs) {
            RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                    .orElseThrow(() -> new IllegalStateException("Room type with id " + dto.getRoomTypeId() + " not found"));

            BookingRoomType bookingRoomType = new BookingRoomType();

            bookingRoomType.setBooking(savedBooking);
            bookingRoomType.setRoomType(roomType);

            bookingRoomType.setRoomTypeName(roomType.getName());
            bookingRoomType.setCheckinDate(dto.getCheckinDate());
            bookingRoomType.setCheckOutDate(dto.getCheckoutDate());
            bookingRoomType.setQuantity(dto.getQuantity());
            bookingRoomType.setRoomPrice(dto.getRoomPrice());
            bookingRoomType.setGuestCount(dto.getGuestCount());
            bookingRoomType.setNumberOfDays(dto.getNumberOfDays());

            bookingRoomTypes.add(bookingRoomTypeRepository.save(bookingRoomType));

        }
        return bookingRepository.save(booking);

    }
}
