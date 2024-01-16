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
        Flight flight = flightRepository.save(flightToSave);
        Plane plane = flight.getPlane();
        List<Seat> seats = flight.getSeats();
        int seatCounter = 1;
        for (int j = 0; j < plane.getType().getBusinessCapacity(); j++) {
            seats.add(Seat.builder()
                    .seatClass(SeatClass.BUSINESS)
                    .flight(flight)
                    .seatNumber(seatCounter++)
                    .build());
            seatRepository.save(seats.get(seats.size()-1));
        }
        for (int j = 0; j < plane.getType().getEconomyCapacity(); j++) {
            seats.add(Seat.builder()
                    .seatClass(SeatClass.ECONOMY)
                    .flight(flight)
                    .seatNumber(seatCounter++)
                    .build());
            seatRepository.save(seats.get(seats.size()-1));
        }
        for (int j = 0; j < plane.getType().getFirstCapacity(); j++) {
            seats.add(Seat.builder()
                    .seatClass(SeatClass.FIRST)
                    .flight(flight)
                    .seatNumber(seatCounter++)
                    .build());
            seatRepository.save(seats.get(seats.size()-1));
        }

        return flight;
    }
    public Flight getFlightById(long id) {
        return flightRepository.findById(id).orElse(null);
    }
}
