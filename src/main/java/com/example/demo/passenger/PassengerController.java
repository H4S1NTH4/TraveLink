package com.example.demo.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getPassengers() {
        return passengerService.getPassengers();
    }

    @GetMapping("{passengerId}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long passengerId) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        return ResponseEntity.ok(passenger);
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        return ResponseEntity.ok(passengerService.createPassenger(passenger));
    }

    @PutMapping("{passengerId}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long passengerId, @RequestBody Passenger passengerDetails) {
        Passenger updatedPassenger = passengerService.updatePassenger(passengerId, passengerDetails);
        return ResponseEntity.ok(updatedPassenger);
    }

    @DeleteMapping("{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long passengerId) {
        passengerService.deletePassenger(passengerId);
        return ResponseEntity.noContent().build();
    }
}
