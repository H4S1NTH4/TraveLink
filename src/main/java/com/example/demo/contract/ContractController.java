package com.example.demo.contract;

import com.example.demo.contract.dto.ContractCreateDTO;
import com.example.demo.contract.dto.ContractUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/contract")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public List<Contract> getContracts() {
        return contractService.getContracts();
    }

    @GetMapping(path = "/hotel/{hotelId}")
    public List<Contract> getContractsByHotelId(@PathVariable Long hotelId) {
        return contractService.getContractsByHotelId(hotelId);
    }


    @PostMapping
    public ResponseEntity<?> createContract(@RequestBody @Valid ContractCreateDTO contractCreateDTO) {
        return ResponseEntity.ok(contractService.createContract(contractCreateDTO));
    }

    @PutMapping(path="{contract_Id}")
    public ResponseEntity<?>updateContract(@PathVariable("contract_Id") Long contract_Id,
                                           @RequestBody @Valid ContractUpdateDTO contractUpdateDTO){
        return ResponseEntity.ok(contractService.updateContract(contract_Id, contractUpdateDTO));
    }

    @DeleteMapping(path = "{contract_Id}")
    public void deleteContract(@PathVariable("contract_Id") Long contract_Id) {
        contractService.deleteContract(contract_Id);
    }
}
