package org.example.flyhigh.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.enums.SeatClass;
import org.example.flyhigh.model.flight.AddFlightRequest;
import org.example.flyhigh.model.flight.AddedFlightModel;
import org.example.flyhigh.model.flight.AddedSeatModel;
import org.example.flyhigh.model.flight.FlightInfoModel;
import org.example.flyhigh.service.AirportService;
import org.example.flyhigh.service.FlightService;
import org.example.flyhigh.service.PlaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return Flight.toFlightInfoModelList(flights);
    }
    @PostMapping("flight")
    public ResponseEntity<AddedFlightModel> addFlight(@Valid @RequestBody AddFlightRequest flightRequest){
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
        if(arrivalTime.isBefore(departureTime)){
            return ResponseEntity.badRequest().build();
        }
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
        AddedFlightModel addedFlightModel = savedFlight.toAddedFlightModel();
        return ResponseEntity.ok(addedFlightModel);
    }

    @GetMapping("flights/search")
    public ResponseEntity<List<FlightInfoModel>> searchFlights(
            @RequestParam("departure") Optional<String> departure,
            @RequestParam("arrival") Optional<String> arrival,
            @RequestParam("date") Optional<LocalDate> date
            ){
        List<Flight> flights = flightService.searchFlights(departure,arrival,date);
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
        return ResponseEntity.ok(flightInfoModels);
    }
}
