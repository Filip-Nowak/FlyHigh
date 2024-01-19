package org.example.flyhigh.repository;

import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.entity.Flight;

import java.time.LocalDate;
import java.util.List;

public interface CustomFlightRepository {
    List<Flight> searchFlights(Airport s, Airport s1, LocalDate localDate);

}
