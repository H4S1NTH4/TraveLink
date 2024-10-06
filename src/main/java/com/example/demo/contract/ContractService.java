package com.example.demo.contract;
import com.example.demo.contract.dto.ContractCreateDTO;
import com.example.demo.contract.dto.ContractUpdateDTO;
import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.hotel.dto.HotelUpdateRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    private final HotelRepository hotelRepository;

    private final ContractMapper contractMapper;

    public ContractService(ContractRepository contractRepository, ContractMapper contractMapper,HotelRepository hotelRepository) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
        this.hotelRepository = hotelRepository;
    }

    @GetMapping
    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }

    public ResponseEntity<String> createContract(ContractCreateDTO contractCreateDTO) {

        //retrieve hotel by Id
        Hotel hotel = hotelRepository.findById(contractCreateDTO.getHotel_Id())
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        //DTO to Contract entity
        Contract contract = contractMapper.toContract(contractCreateDTO);

        contract.setHotel(hotel);
        contractRepository.save(contract);
        return ResponseEntity.status(201).body("Contract created, Id: "+contract.getContract_Id());
    }

    @Transactional
    public void updateContract(Long contractId, @Valid ContractUpdateDTO contractUpdateDTO) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new IllegalArgumentException("Contract with id " + contractId + " does not exist"));
        Hotel hotel =hotelRepository.findById(contractUpdateDTO.getHotel_Id())
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        contract.setHotel(hotel);
        contractMapper.updateContractFromDto(contractUpdateDTO, contract);
        contractRepository.save(contract);
    }

    public void deleteContract(Long contractId) {
        boolean exists = contractRepository.existsById(contractId);
        if (!exists) {
            throw new IllegalArgumentException("Contract with id " + contractId + " does not exist");
        }
        contractRepository.deleteById(contractId);
    }
}
