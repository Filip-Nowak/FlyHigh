package org.example.flyhigh.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.model.airport.AddAirportRequest;
import org.example.flyhigh.model.airport.AddedAirportModel;
import org.example.flyhigh.model.airport.AirportInfoModel;
import org.example.flyhigh.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class AirportRestController {
    private final AirportService airportService;

    @GetMapping("airports")
    public ResponseEntity<List<AirportInfoModel>> getAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(Airport.toAirportInfoModels(airports));
    }

    @PostMapping("airport")
    public ResponseEntity<AddedAirportModel> addAirport(@Valid @RequestBody AddAirportRequest airportRequest) {
        Airport airportToAdd = Airport.builder()
                .city(airportRequest.getCity())
                .build();
        Airport airport = airportService.addAirport(airportToAdd);
        return ResponseEntity.ok(airport.toAddedAirportModel());
    }
}
