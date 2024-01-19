package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.enums.SeatClass;
import org.example.flyhigh.repository.FlightRepository;
import org.example.flyhigh.repository.SeatRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;
    private SeatRepository seatRepository;
    AirportService airportService;

    public Flight addFlight(Flight flightToSave) {
        Flight flight = flightRepository.save(flightToSave);
        for (Seat seat : flight.getSeats()) {
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

    public List<Flight> searchFlights(
            Optional<String> optionalDeparture,
            Optional<String> optionalArrival,
            Optional<LocalDate> date) {
        Airport departure = null;
        Airport arrival = null;
        if(optionalDeparture.isPresent()) {
            departure = airportService.getAirportByName(optionalDeparture.get()).orElse(null);
        }
        if(optionalArrival.isPresent()) {
            arrival = airportService.getAirportByName(optionalArrival.get()).orElse(null);
        }
        return flightRepository.searchFlights(departure, arrival, date.orElse(null));
    }
}
