package com.example.demo.booking;

import com.example.demo.bookingRoomType.BookingRoomType;
import com.example.demo.bookingRoomType.BookingRoomTypeRepository;
import com.example.demo.bookingRoomType.DTO.BookingRoomTypeCreateDTO;
import com.example.demo.bookingSupplement.BookingSupplement;
import com.example.demo.bookingSupplement.BookingSupplementRepository;
import com.example.demo.bookingSupplement.CreateBookingSupDTO;
import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.room_type.RoomType;
import com.example.demo.room_type.RoomTypeRepository;
import com.example.demo.season.Season;
import com.example.demo.supplement.Supplement;
import com.example.demo.supplement.SupplementRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final BookingRoomTypeRepository bookingRoomTypeRepository;
    private final SupplementRepository supplementRepository;
    private final BookingSupplementRepository bookingSupplementRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, HotelRepository hotelRepository, UserRepository userRepository, RoomTypeRepository roomTypeRepository, BookingRoomTypeRepository bookingRoomTypeRepository, SupplementRepository supplementRepository, BookingSupplementRepository bookingSupplementRepository) {
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.bookingRoomTypeRepository = bookingRoomTypeRepository;
        this.supplementRepository = supplementRepository;
        this.bookingSupplementRepository = bookingSupplementRepository;
    }

    // convert to hashset and set in booking
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    //get booking by user id
    public List<Booking> getBookingsByUserId(Long user_Id) {

        User user = userRepository.findById(user_Id)
                .orElseThrow(() -> new IllegalArgumentException("User with id" +user_Id+ " not found"));

        return bookingRepository.findBookingsByUserId(user_Id);
    }



    //create method createBooking
    public Booking createBooking(Booking booking, Long hotelId, Long userId,
                                 List<BookingRoomTypeCreateDTO> roomTypeDTOs,
                                 List<CreateBookingSupDTO> supplementDTOs) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel with id: " + hotelId + " not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id: " + userId + " not found"));

        booking.setHotel(hotel);
        booking.setUser(user);

        //Save Booking
        Booking savedBooking = bookingRepository.save(booking);

        //Check and Book rooms
//        List<BookingRoomType> bookingRoomTypes = new ArrayList<>();
        Set<BookingRoomType> bookingRoomTypes = new HashSet<>();

        for (BookingRoomTypeCreateDTO dto : roomTypeDTOs) {
            RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                    .orElseThrow(() -> new IllegalStateException("Room type with id " + dto.getRoomTypeId() + " not found"));

            //validate room quantity
//            Integer availRoom = bookingRoomTypeRepository.getAvailableRoomQuantity(roomType.getRoomTypeId(),dto.getCheckinDate(),
//                    dto.getCheckoutDate(),hotelId);
//
//            System.out.println("available rooms for room type "+roomType.getName()+"is : "+availRoom);
//
            if(bookingRoomTypeRepository.getAvailableRoomQuantity(roomType.getRoomTypeId(),dto.getCheckinDate(),
                    dto.getCheckoutDate(),hotelId) < dto.getQuantity()) {
                throw new IllegalStateException("Not enough rooms available.");
            }



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

//        List<BookingSupplement> bookingSupplements = new ArrayList<>();
        Set<BookingSupplement> bookingSupplements = new HashSet<>();
        for(CreateBookingSupDTO dto : supplementDTOs) {

            BookingSupplement bookingSupplement = new BookingSupplement();

            Supplement supplement = supplementRepository.findById(dto.getSupplementId())
                    .orElseThrow(() -> new IllegalStateException("Supplement with id " + dto.getSupplementId() + "not found"));


            bookingSupplement.setBooking(savedBooking);
            bookingSupplement.setSupplement(supplement);

            bookingSupplement.setSupplementName(supplement.getSupplementName());

            bookingSupplement.setSupplementPrice(dto.getSupplementPrice());
            bookingSupplement.setSupplementQuantity(dto.getSupplementQuantity());
            bookingSupplement.setNoOfDays(dto.getNoOfDays());

            bookingSupplements.add(bookingSupplementRepository.save(bookingSupplement));
        }

        savedBooking.setBookingRoomTypes(bookingRoomTypes);
        savedBooking.setBookingSupplements(bookingSupplements);
        return bookingRepository.save(booking);

    }

//    public void checkAvailableRooms(){
//
//        LocalDate checkInDate = LocalDate.parse("2024-12-01");
//        LocalDate checkOutDate = LocalDate.parse("2024-12-05");
//        Long roomTypeId = 7L; // Example room type ID (Long type)
//        Long hotelId = 32L; // Example hotel ID (Long type)
//
////        Integer availRoom = bookingRoomTypeRepository.getAvailableRoomQuantity(roomTypeId,checkInDate,checkOutDate,hotelId);
////
////        System.out.println("available rooms for room type " + availRoom);
//    }

}
