package org.example.flyhigh.rest;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.enums.SeatClass;
import org.example.flyhigh.model.AddFlightRequest;
import org.example.flyhigh.model.AddedFlightModel;
import org.example.flyhigh.model.AddedSeatModel;
import org.example.flyhigh.model.FlightInfoModel;
import org.example.flyhigh.service.AirportService;
import org.example.flyhigh.service.FlightService;
import org.example.flyhigh.service.PlaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.PSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class FlightRestController {
    private final FlightService flightService;
    private final AirportService airportService;
    private final PlaneService planeService;
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
    @PostMapping("flight")
    public ResponseEntity<AddedFlightModel> addFlight(@RequestBody AddFlightRequest flightRequest){
        System.out.println("XDDDD");
        Optional<Airport> optionalDeparture = airportService.getAirportByName(flightRequest.getDeparture());
        if(optionalDeparture.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Airport> optionalArrival = airportService.getAirportByName(flightRequest.getArrival());
        if(optionalArrival.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Plane> optionalPlane = planeService.getPlaneById(flightRequest.getPlaneId());
        if(optionalPlane.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Plane plane = optionalPlane.get();
        Airport departure = optionalDeparture.get();
        Airport arrival = optionalArrival.get();
        List<Seat> seats = new LinkedList<>();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime arrivalTime = LocalDateTime.parse(flightRequest.getArrivalTime(),formatter);
        LocalDateTime departureTime = LocalDateTime.parse(flightRequest.getDepartureTime(),formatter);
        Flight flight = Flight.builder()
                .arrivalTime(arrivalTime)
                .departureTime(departureTime)
                .arrival(arrival)
                .departure(departure)
                .businessPrice(flightRequest.getBusinessPrice())
                .economyPrice(flightRequest.getEconomyPrice())
                .firstPrice(flightRequest.getFirstPrice())
                .plane(plane)
                .build();
        int seatCounter=1;
        for(int i=0;i<plane.getType().getFirstCapacity();i++){
            seats.add(Seat.builder()
                    .flight(flight)
                    .seatNumber(seatCounter++)
                    .seatClass(SeatClass.FIRST)
                    .build());
        }
        for(int i=0;i<plane.getType().getBusinessCapacity();i++){
            seats.add(Seat.builder()
                    .flight(flight)
                    .seatNumber(seatCounter++)
                    .seatClass(SeatClass.BUSINESS)
                    .build());
        }
        for(int i=0;i<plane.getType().getEconomyCapacity();i++){
            seats.add(Seat.builder()
                    .flight(flight)
                    .seatNumber(seatCounter++)
                    .seatClass(SeatClass.ECONOMY)
                    .build());
        }
        flight.setSeats(seats);
        Flight savedFlight= flightService.addFlight(flight);
        plane.addFlight(savedFlight);
        planeService.addPlane(plane);
        List<AddedSeatModel> addedSeatModels = new LinkedList<>();
        for(Seat seat:savedFlight.getSeats()){
            System.out.println(seat);
            System.out.println(seat.getId());
            long id = seat.getId();
            addedSeatModels.add(AddedSeatModel.builder()
                    .seatNumber(seat.getSeatNumber())
                    .seatClass(seat.getSeatClass())
                    .build());
            System.out.println(id);
            addedSeatModels.get(addedSeatModels.size()-1).setId(id);
        }
        AddedFlightModel addedFlightModel = AddedFlightModel.builder()
                .id(savedFlight.getId())
                .arrival(savedFlight.getArrival().getCity())
                .departure(savedFlight.getDeparture().getCity())
                .arrivalTime(savedFlight.getArrivalTime())
                .departureTime(savedFlight.getDepartureTime())
                .businessPrice(savedFlight.getBusinessPrice())
                .economyPrice(savedFlight.getEconomyPrice())
                .firstPrice(savedFlight.getFirstPrice())
                .planeId(savedFlight.getPlane().getId())
                .seats(addedSeatModels)
                .build();
        return ResponseEntity.ok(addedFlightModel);
    }
}
