package org.example.flyhigh.service;

import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private FlightRepository flightRepository;
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}
