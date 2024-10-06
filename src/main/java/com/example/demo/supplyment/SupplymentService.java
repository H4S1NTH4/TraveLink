package com.example.demo.supplyment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplymentService {

    private final SupplymentRepository supplymentRepository;

    @Autowired
    public SupplymentService(SupplymentRepository supplymentRepository) {
        this.supplymentRepository = supplymentRepository;
    }

    public List<Supplyment> getSupplyments() {
        return supplymentRepository.findAll();
    }

    public Supplyment getSupplymentById(Long supplymentId) {
        return supplymentRepository.findById(supplymentId)
                .orElseThrow(() -> new IllegalStateException("Supplyment with id " + supplymentId + " not found"));
    }

    public Supplyment createSupplyment(Supplyment supplyment) {
        return supplymentRepository.save(supplyment);
    }

    public Supplyment updateSupplyment(Long supplymentId, Supplyment supplymentDetails) {
        Supplyment supplyment = supplymentRepository.findById(supplymentId)
                .orElseThrow(() -> new IllegalStateException("Supplyment with id " + supplymentId + " not found"));

        supplyment.setSupplymentName(supplymentDetails.getSupplymentName());
        supplyment.setSupplymentDescription(supplymentDetails.getSupplymentDescription());
        return supplymentRepository.save(supplyment);
    }

    public void deleteSupplyment(Long supplymentId) {
        supplymentRepository.deleteById(supplymentId);
    }
}
