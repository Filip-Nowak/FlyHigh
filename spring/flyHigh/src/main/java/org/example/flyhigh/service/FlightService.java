package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.enums.SeatClass;
import org.example.flyhigh.repository.FlightRepository;
import org.example.flyhigh.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;
    private SeatRepository seatRepository;
    public Flight addFlight(Flight flightToSave) {
        Flight flight= flightRepository.save(flightToSave);
        for(Seat seat:flight.getSeats()){
            seat.setFlight(flight);
            seatRepository.save(seat);
        }
        return flightRepository.findById(flight.getId()).orElse(null);
    }
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    public Flight getFlightById(long id) {
        return flightRepository.findById(id).orElse(null);
    }
}
