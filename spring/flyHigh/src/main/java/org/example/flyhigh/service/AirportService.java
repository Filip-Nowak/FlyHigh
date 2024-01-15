package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {
    AirportRepository airportRepository;
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }
    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }
    public Airport getAirportByName(String city) {
        return airportRepository.findByCity(city);
    }
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}
