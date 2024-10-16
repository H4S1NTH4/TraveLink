package com.example.demo.supplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/supplement")
public class SupplementController {

    private final SupplementService supplementService;

    @Autowired
    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @GetMapping
    public List<Supplement> getSupplements() {
        return supplementService.getSupplements();
    }

    @GetMapping("{supplementId}")
    public ResponseEntity<Supplement> getSupplementById(@PathVariable Long supplementId) {
        Supplement supplement = supplementService.getSupplementById(supplementId);
        return ResponseEntity.ok(supplement);
    }

    @PostMapping
    public ResponseEntity<?> createSupplement(@RequestBody Supplement supplement) {
        return ResponseEntity.ok(supplementService.createSupplement(supplement));
    }

    @PutMapping("{supplementId}")
    public ResponseEntity<Supplement> updateSupplement(@PathVariable Long supplementId, @RequestBody Supplement supplementDetails) {
        Supplement updatedSupplement = supplementService.updateSupplement(supplementId, supplementDetails);
        return ResponseEntity.ok(updatedSupplement);
    }

    @DeleteMapping("{supplementId}")
    public ResponseEntity<Void> deleteSupplement(@PathVariable Long supplementId) {
        supplementService.deleteSupplement(supplementId);
        return ResponseEntity.noContent().build();
    }
}
