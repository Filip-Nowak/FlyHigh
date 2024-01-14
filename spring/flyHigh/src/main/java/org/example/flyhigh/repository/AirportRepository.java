package org.example.flyhigh.repository;

import org.example.flyhigh.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByCity(String city);
}
