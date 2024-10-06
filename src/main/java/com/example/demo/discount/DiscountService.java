package com.example.demo.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getDiscounts() {
        return discountRepository.findAll();
    }

    public Discount getDiscountById(Long discountId) {
        return discountRepository.findById(discountId)
                .orElseThrow(() -> new IllegalStateException("Discount with id " + discountId + " not found"));
    }

    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public Discount updateDiscount(Long discountId, Discount discountDetails) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new IllegalStateException("Discount with id " + discountId + " not found"));

        discount.setDiscountName(discountDetails.getDiscountName());
        discount.setDiscountDescription(discountDetails.getDiscountDescription());
        return discountRepository.save(discount);
    }

    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }
}
