package org.example.flyhigh.repository;

import org.example.flyhigh.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
@Query("SELECT DISTINCT p FROM Plane p")
List<Plane> findAvailablePlanesInTimeInterval(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}