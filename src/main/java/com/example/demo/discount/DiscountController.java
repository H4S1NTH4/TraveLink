package com.example.demo.discount;

import com.example.demo.supplyment.Supplyment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        @PostMapping
        public ResponseEntity<?> createDiscount(@RequestBody Discount discount) {
            return ResponseEntity.ok(discountService.createDiscount(discount));
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

