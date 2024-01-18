package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.repository.PlaneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        List<Plane> planes =planeRepository.findAll();
        Plane plane;
        for(int i=0;i<planes.size();i++){
            boolean cont=true;
            plane = planes.get(i);
            for(Flight flight:plane.getFlights()){
                if(
                        ((departureTime.isAfter(flight.getDepartureTime())&&departureTime.isBefore(flight.getArrivalTime()))||
                        arrivalTime.isAfter(flight.getDepartureTime())&&arrivalTime.isBefore(flight.getArrivalTime()))||
                        (departureTime.isBefore(flight.getDepartureTime())&&arrivalTime.isAfter(flight.getArrivalTime()))
                )
                {
                    cont=false;
                    break;
                }
            }
            if(cont){
                return plane;
            }
        }

return null;

    }
    public Optional<Plane> getPlaneById(Long id) {
        return planeRepository.findById(id);
    }
}
