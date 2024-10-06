package com.example.demo.contract;

import com.example.demo.contract.dto.ContractCreateDTO;
import com.example.demo.contract.dto.ContractUpdateDTO;
import com.example.demo.hotel.HotelMapper;
import com.example.demo.hotel.dto.HotelCreateDTO;
import com.example.demo.hotel.dto.HotelUpdateRequest;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import  org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);
    Contract toContract(ContractCreateDTO dto);

    void updateContractFromDto(ContractUpdateDTO contractUpdateDTO, @MappingTarget Contract contract);
}


