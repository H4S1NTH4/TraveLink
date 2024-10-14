package com.example.demo.supplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplementService {

    private final SupplementRepository supplementRepository;

    @Autowired
    public SupplementService(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }

    public List<Supplement> getSupplements() {
        return supplementRepository.findAll();
    }

    public Supplement getSupplementById(Long supplementId) {
        return supplementRepository.findById(supplementId)
                .orElseThrow(() -> new IllegalStateException("Supplement with id " + supplementId + " not found"));
    }

    public Supplement createSupplement(Supplement supplement) {
        return supplementRepository.save(supplement);
    }

    public Supplement updateSupplement(Long supplementId, Supplement supplementDetails) {
        Supplement supplement = supplementRepository.findById(supplementId)
                .orElseThrow(() -> new IllegalStateException("Supplement with id " + supplementId + " not found"));

        supplement.setSupplementName(supplementDetails.getSupplementName());
        supplement.setSupplementDescription(supplementDetails.getSupplementDescription());
        return supplementRepository.save(supplement);
    }

    public void deleteSupplement(Long supplementId) {
        Supplement supplement = supplementRepository.findById(supplementId)
                        .orElseThrow(() -> new IllegalStateException("Supplement with id " + supplementId + " not found"));
        supplementRepository.deleteById(supplementId);
    }
}
