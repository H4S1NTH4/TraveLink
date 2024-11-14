package com.example.demo.discount;

import com.example.demo.season.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping(path = "api/v1/discount")
public class DiscountController {

        private final DiscountService discountService;

        @Autowired
        public DiscountController(DiscountService discountService) {
            this.discountService = discountService;
        }

        @GetMapping
        public List<Discount> getDiscounts() {
            return discountService.getDiscounts();
        }

        @GetMapping("{discountId}")
        public ResponseEntity<Discount> getDiscountById(@PathVariable Long discountId) {
            Discount discount = discountService.getDiscountById(discountId);
            return ResponseEntity.ok(discount);
        }

    @GetMapping("/byContract/{contract_Id}")
    public ResponseEntity<List<Discount>> getDiscountsByContractId(@PathVariable("contract_Id") Long contract_Id){
        List<Discount> discounts  = discountService.getDiscountsByContractId(contract_Id);
        return ResponseEntity.ok(discounts);

    }

    @GetMapping("/byHotel/{hotelId}")
    public ResponseEntity<Discount> getAvailableDiscount(@PathVariable("hotelId") Long hotelId,
                                                                @RequestParam LocalDate checkInDate,
                                                                @RequestParam LocalDate checkOutDate,
                                                               @RequestParam double bookingCost){

        Discount discount  = discountService.getAvailableDiscount(hotelId,checkInDate,checkOutDate,bookingCost);
        return ResponseEntity.ok(discount);

    }

    @PostMapping("contractId/{contractId}")
        public ResponseEntity<?> createDiscount(@RequestBody Discount discount,
                                                @PathVariable Long contractId) {
            return ResponseEntity.ok(discountService.createDiscount(discount,contractId));
        }


        @PutMapping("{discountId}")
        public ResponseEntity<Discount> updateDiscount(@PathVariable Long discountId, @RequestBody Discount discountDetails) {
            Discount updatedDiscount = discountService.updateDiscount(discountId, discountDetails);
            return ResponseEntity.ok(updatedDiscount);
        }

        @DeleteMapping("{discountId}")
        public ResponseEntity<Void> deleteDiscount(@PathVariable Long discountId) {
            discountService.deleteDiscount(discountId);
            return ResponseEntity.noContent().build();
        }
    }

