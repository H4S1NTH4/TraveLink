package com.example.demo.supplyment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/supplyment")
public class SupplymentController {

    private final SupplymentService supplymentService;

    @Autowired
    public SupplymentController(SupplymentService supplymentService) {
        this.supplymentService = supplymentService;
    }

    @GetMapping
    public List<Supplyment> getSupplyments() {
        return supplymentService.getSupplyments();
    }

    @GetMapping("{supplymentId}")
    public ResponseEntity<Supplyment> getSupplymentById(@PathVariable Long supplymentId) {
        Supplyment supplyment = supplymentService.getSupplymentById(supplymentId);
        return ResponseEntity.ok(supplyment);
    }

    @PostMapping
    public ResponseEntity<?> createSupplyment(@RequestBody Supplyment supplyment) {
        return ResponseEntity.ok(supplymentService.createSupplyment(supplyment));
    }

    @PutMapping("{supplymentId}")
    public ResponseEntity<Supplyment> updateSupplyment(@PathVariable Long supplymentId, @RequestBody Supplyment supplymentDetails) {
        Supplyment updatedSupplyment = supplymentService.updateSupplyment(supplymentId, supplymentDetails);
        return ResponseEntity.ok(updatedSupplyment);
    }

    @DeleteMapping("{supplymentId}")
    public ResponseEntity<Void> deleteSupplyment(@PathVariable Long supplymentId) {
        supplymentService.deleteSupplyment(supplymentId);
        return ResponseEntity.noContent().build();
    }
}
