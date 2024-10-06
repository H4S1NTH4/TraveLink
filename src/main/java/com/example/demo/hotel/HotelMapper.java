package com.example.demo.hotel;

import com.example.demo.hotel.dto.HotelCreateDTO;
import com.example.demo.hotel.dto.HotelUpdateRequest;
import org.mapstruct.Mapper;
import  org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    Hotel toHotel(HotelCreateDTO hotelCreateDTO);

   // HotelUpdateResponse toResponse(Hotel hotel);

    void updateHotelFromDto(HotelUpdateRequest dto, @MappingTarget Hotel hotel);
}
