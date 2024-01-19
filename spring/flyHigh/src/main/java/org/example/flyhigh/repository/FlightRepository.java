package org.example.flyhigh.repository;

import org.example.flyhigh.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long>, CustomFlightRepository{
}
