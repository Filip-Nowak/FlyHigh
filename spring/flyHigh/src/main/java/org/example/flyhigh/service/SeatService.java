package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    public List<Seat> getSeatByFlight(Flight flight){
        return seatRepository.findAllByFlight(flight);
    }
    public List<Seat> getSeatsByNumbers(List<Integer> seatNumbers, Flight flight){
        return seatRepository.findSeatsByNumbers(seatNumbers, flight);
    }
}
