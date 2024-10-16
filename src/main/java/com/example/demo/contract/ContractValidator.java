package com.example.demo.contract;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractValidator {

 //  /remake following functions to validate the contract
            public void validateStartAndEnd(Contract newContract){
                if (newContract.getStartDate() == null || newContract.getEndDate() == null) {
                    throw new IllegalArgumentException("Contract start date and end date must not be null.");
                }

                if (!newContract.getStartDate().isBefore(newContract.getEndDate())) {
                    throw new IllegalArgumentException("The contract start date must be before the end date.");
                }

        }

        public void validateNoOverlap(Contract newContract, ContractRepository contractRepository, Long hotel_Id) {
            //Have to get this list by hotel_Id
                List<Contract> existingContracts = contractRepository.findContractsByHotelId(hotel_Id);

            for (Contract existingContract : existingContracts) {
                if (isOverlapping(existingContract, newContract)) {
                    throw new IllegalArgumentException("The new contract overlaps with an existing Contract.");
                }
            }
        }

        //Helper method for the validateNoOverlap
        private boolean isOverlapping(Contract existingContract, Contract newContract) {
            return !(newContract.getEndDate().isBefore(existingContract.getStartDate()) ||
                    newContract.getStartDate().isAfter(existingContract.getEndDate()));
        }
    }


// validateStartAndEnd(Contract)
//validateNoOverlap(Contract, ContractRepository)

