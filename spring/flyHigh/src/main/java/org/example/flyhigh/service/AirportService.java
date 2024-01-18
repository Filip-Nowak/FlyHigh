package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportService {
    AirportRepository airportRepository;
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }
    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }
    public Optional<Airport> getAirportByName(String city) {
        return airportRepository.findByCity(city);
    }
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}
