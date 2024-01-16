package org.example.flyhigh.repository;

import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.PlaneAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
//    @Query("SELECT DISTINCT p FROM Plane p JOIN p.flights f " +
//            "WHERE NOT EXISTS (" +
//            "  SELECT 1 FROM Flight f2 " +
//            "  WHERE f2.plane = p " +
//            "  AND (:start BETWEEN f2.departureTime AND f2.arrivalTime AND :end BETWEEN f2.departureTime AND f2.arrivalTime)" +
//            ")")
//    List<Plane> findAvailablePlanesInTimeInterval(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
@Query("SELECT p FROM Plane p join p.flights f WHERE NOT EXISTS (" +
        "SELECT f1 FROM Flight f1 WHERE (:start BETWEEN f1.departureTime AND f1.arrivalTime AND :end BETWEEN f1.departureTime AND f1.arrivalTime" +
        ")OR (:start<f1.departureTime AND :end>f1.arrivalTime))")
List<Plane> test(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

@Query("SELECT p from Plane p where not exists(select pa from p.planeAvailabilities pa where pa.date=:date)")
List<Plane> availablePlanes(@Param("date") LocalDate date);
}