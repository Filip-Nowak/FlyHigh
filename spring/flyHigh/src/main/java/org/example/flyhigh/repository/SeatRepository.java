package org.example.flyhigh.repository;

import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByFlight(Flight flight);
    Seat findBySeatNumberAndFlight(int seatNumber, Flight flight);
    @Query("SELECT s FROM Seat s WHERE s.seatNumber IN ?1 AND s.flight = ?2")
    List<Seat> findSeatsByNumbers(List<Integer> seatNumbers, Flight flight);
}
