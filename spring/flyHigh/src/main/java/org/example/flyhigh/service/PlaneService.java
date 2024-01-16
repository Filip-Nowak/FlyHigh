package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.repository.PlaneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PlaneService {
    private PlaneRepository planeRepository;

    public Plane addPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    public Plane getAvailablePlane(LocalDateTime departureTime, LocalDateTime arrivalTime) {
        List<Plane> planes = planeRepository.findAll();
        Collections.shuffle(planes);
        Plane plane;
        for (int i = 0; i < planes.size(); i++) {
            boolean cont = true;
            plane = planes.get(i);
            for (Flight flight : plane.getFlights()) {
                if (
                        ((departureTime.isAfter(flight.getDepartureTime()) && departureTime.isBefore(flight.getArrivalTime())) ||
                                arrivalTime.isAfter(flight.getDepartureTime()) && arrivalTime.isBefore(flight.getArrivalTime())) ||
                                (departureTime.isBefore(flight.getDepartureTime()) && arrivalTime.isAfter(flight.getArrivalTime()))
                ) {
                    cont = false;
                    break;
                }
            }
            if (cont) {
                return plane;
            }
        }

        return null;

    }

    public List<Plane> test(LocalDateTime start, LocalDateTime end) {
        return planeRepository.availablePlanes(start.toLocalDate());
    }
}
