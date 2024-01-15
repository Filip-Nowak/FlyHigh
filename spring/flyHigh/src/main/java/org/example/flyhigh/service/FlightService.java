package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }
    public Flight getFlightById(long id) {
        return flightRepository.findById(id).orElse(null);
    }
}
