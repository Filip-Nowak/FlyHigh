package org.example.flyhigh.rest;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.model.FlightInfoModel;
import org.example.flyhigh.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class FlightRestController {
    private final FlightService flightService;
    @GetMapping("flights")
    public List<FlightInfoModel> getFlights()
    {
        List<Flight> flights = flightService.getAllFlights();
        List<FlightInfoModel> flightInfoModels = new LinkedList<>();
        for(Flight flight:flights){
            flightInfoModels.add(FlightInfoModel.builder()
                    .arrival(flight.getArrival().getCity())
                    .departure(flight.getDeparture().getCity())
                    .arrivalTime(flight.getArrivalTime())
                    .departureTime(flight.getDepartureTime())
                    .businessPrice(flight.getBusinessPrice())
                    .economyPrice(flight.getEconomyPrice())
                    .firstPrice(flight.getFirstPrice())
                    .build());
        }
        return flightInfoModels;
    }
}
