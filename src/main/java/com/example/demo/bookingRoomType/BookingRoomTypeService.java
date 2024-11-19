package com.example.demo.bookingRoomType;

import com.example.demo.booking.Booking;
import com.example.demo.booking.BookingRepository;
import com.example.demo.bookingRoomType.DTO.BookingRoomTypeCreateDTO;
import com.example.demo.roomSeason.RoomSeasonRepository;
import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingRoomTypeService {

    private final BookingRoomTypeRepository bookingRoomTypeRepository;
    private final BookingRepository bookingRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomSeasonRepository roomSeasonRepository;

    @Autowired
    public BookingRoomTypeService(BookingRoomTypeRepository bookingRoomTypeRepository, BookingRepository bookingRepository, RoomTypeRepository roomTypeRepository, RoomSeasonRepository roomSeasonRepository) {
        this.bookingRoomTypeRepository = bookingRoomTypeRepository;
        this.bookingRepository = bookingRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.roomSeasonRepository = roomSeasonRepository;
    }

    public List<BookingRoomType> getBookingRoomTypes() {
        return bookingRoomTypeRepository.findAll();
    }

    //create booking room types
    @Transactional
    public ResponseEntity<List<BookingRoomType>> createBookingRoomType(Long bookingId,List<BookingRoomTypeCreateDTO> bookingRoomTypesDTOs) {

        // Average room price as param ,
        // room type name form roomType tbl

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalStateException("Booking with id " + bookingId + " not found"));

        // Validate (implement later ) no of free rooms >= quantity

        List<BookingRoomType> bookingRoomTypes = new ArrayList<>();

        for (BookingRoomTypeCreateDTO dto : bookingRoomTypesDTOs) {

            BookingRoomType bookingRoomType = new BookingRoomType();

            RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                            .orElseThrow(() -> new IllegalStateException("Room type with id " + dto.getRoomTypeId() + " not found"));

//            RoomSeason roomSeason = roomSeasonRepository.findById(dto.getRoomSeasonId())
//                    .orElseThrow(() -> new IllegalStateException("Room season with id " + dto.getRoomSeasonId() + " not found"));

            bookingRoomType.setBooking(booking);
            bookingRoomType.setCheckinDate(dto.getCheckinDate());
            bookingRoomType.setCheckOutDate(dto.getCheckoutDate());

//            bookingRoomType.setRoomSeason(roomSeason);
            bookingRoomType.setRoomType(roomType);
            bookingRoomType.setRoomTypeName(roomType.getName());
            bookingRoomType.setGuestCount(dto.getGuestCount());
            bookingRoomType.setRoomPrice(dto.getRoomPrice());
            bookingRoomType.setQuantity(dto.getQuantity());
            bookingRoomType.setNumberOfDays(dto.getNumberOfDays());

            bookingRoomTypes.add(bookingRoomTypeRepository.save(bookingRoomType));
        }

        return ResponseEntity.ok(bookingRoomTypes);

//        RoomType roomType = roomTypeRepository.findById()
//                .orElseThrow(() -> new IllegalStateException("Room Type with id " + roomTypeId + " not found"));
//
//        bookingRoomType.setRoomTypeName(roomType.getName());
//        bookingRoomType.setCapacity(roomType.getCapacity());
//        bookingRoomType.setBooking(booking);
//        bookingRoomType.setRoomType(roomType);
//
//         bookingRoomTypeRepository.save(bookingRoomType);
//        return ResponseEntity.ok(bookingRoomType);
    }

   /* public ResponseEntity<BookingRoomType> updateBookingRoomType(Long bookingRoomTypeId, Long roomSeasonId, BookingRoomType bookingRoomType) {

        BookingRoomType existingBookingRoomType = bookingRoomTypeRepository.findById(bookingRoomTypeId)
                .orElseThrow(() -> new IllegalStateException("BookingRoomType with id " + bookingRoomTypeId + " not found"));

        RoomSeason roomSeason = roomSeasonRepository.findById(roomSeasonId)
                .orElseThrow(() -> new IllegalStateException("RoomSeason with id " + roomSeasonId + " not found"));




        // Validate no of free rooms >= quantity


        return null;
    }  */
}
