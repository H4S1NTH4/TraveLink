package com.example.demo.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new IllegalStateException("Passenger with id " + passengerId + " not found"));
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Long passengerId, Passenger passengerDetails) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new IllegalStateException("Passenger with id " + passengerId + " not found"));

        passenger.setName(passengerDetails.getName());
        passenger.setEmail(passengerDetails.getEmail());
        passenger.setPhone(passengerDetails.getPhone());
        passenger.setNic(passengerDetails.getNic());

        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }
}
