package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.PlaneAvailability;
import org.example.flyhigh.repository.FlightRepository;
import org.example.flyhigh.repository.PlaneAvailabilityRepository;
import org.example.flyhigh.repository.PlaneRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;
    private PlaneRepository planeRepository;
    private PlaneAvailabilityRepository planeAvailabilityRepository;

    public void addFlight(Flight flightTosave) {

        System.out.println(flightTosave);
        Flight flight=flightRepository.save(flightTosave);
        Plane plane = flight.getPlane();
        PlaneAvailability planeAvailability = PlaneAvailability.builder()
                .plane(plane)
                .date(flight.getDepartureTime().toLocalDate())
                .build();
        planeAvailabilityRepository.save(planeAvailability);
        if(flight.getArrivalTime().getDayOfMonth()!=flight.getDepartureTime().getDayOfMonth()){
            PlaneAvailability planeAvailability2 = PlaneAvailability.builder()
                    .plane(plane)
                    .date(flight.getArrivalTime().toLocalDate())
                    .build();
            planeAvailabilityRepository.save(planeAvailability2);
        }

    }

    public Flight getFlightById(long id) {
        return flightRepository.findById(id).orElse(null);
    }
}
