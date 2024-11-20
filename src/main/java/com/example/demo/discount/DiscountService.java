package com.example.demo.discount;

import com.example.demo.contract.Contract;
import com.example.demo.contract.ContractRepository;
import com.example.demo.season.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, ContractRepository contractRepository) {
        this.discountRepository = discountRepository;
        this.contractRepository = contractRepository;
    }

    public List<Discount> getDiscounts() {
        return discountRepository.findAll();
    }

    public Discount getDiscountById(Long discountId) {
        return discountRepository.findById(discountId)
                .orElseThrow(() -> new IllegalStateException("Discount with id " + discountId + " not found"));
    }

    public Discount createDiscount(Discount discount,Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new IllegalStateException("Contract with id " + contractId + " not found"));

        discount.setContract(contract);

        return discountRepository.save(discount);
    }

    public Discount updateDiscount(Long discountId, Discount discountDetails) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new IllegalStateException("Discount with id " + discountId + " not found"));

        discount.setDiscountName(discountDetails.getDiscountName());
        discount.setDiscountDescription(discountDetails.getDiscountDescription());
        discount.setType(discountDetails.getType());
        discount.setValue(discountDetails.getValue());
        discount.setDaysPriorArrival(discountDetails.getDaysPriorArrival());
        discount.setEndDate(discountDetails.getEndDate());
        discount.setStartDate(discountDetails.getStartDate());
        discount.setIsActive(discountDetails.getIsActive());
        discount.setMinBookingCost(discountDetails.getMinBookingCost());
        return discountRepository.save(discount);
    }

    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }

    public List<Discount> getDiscountsByContractId(Long contract_Id) {


            Contract contract = contractRepository.findById(contract_Id)
                    .orElseThrow(()-> new IllegalArgumentException("Contract not found with Id: "));

        List<Discount> discounts= discountRepository.findDiscountByContractId(contract.getContract_Id());
        return discounts;
    }

    public Optional<Discount> getAvailableDiscount(Long hotel_Id,
                                                  LocalDate checkInDate,
                                                  LocalDate checkOutDate,
                                                  double bookingCost) {

        Contract contract = contractRepository.findMatchingContract(hotel_Id,checkInDate/*,checkOutDate*/)
                .orElseThrow(()-> new IllegalArgumentException("Contract not found with Id: "));

        List<Discount> discounts= discountRepository.findDiscountByContractId(contract.getContract_Id());

        // Current date
        LocalDate currentDate = LocalDate.now();

        // Filter discounts based on daysPriorArrival and minBookingCost, then find the one with the maximum value
        return discounts.stream()
                .filter(discount -> {
                    // Calculate the difference between check-in date and current date
                    long daysBetween = ChronoUnit.DAYS.between(currentDate, checkInDate);

                    // Apply the conditions for daysPriorArrival and minBookingCost
                    return daysBetween >= discount.getDaysPriorArrival() && bookingCost >= discount.getMinBookingCost();
                })
                .max(Comparator.comparingDouble(Discount::getValue)); // Get the discount with the maximum value
    }




}//end of class

