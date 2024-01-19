package org.example.flyhigh.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.entity.Flight;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
@AllArgsConstructor
public class FlightRepositoryImpl implements CustomFlightRepository {
    EntityManager entityManager;

    @Override
    public List<Flight> searchFlights(Airport departure, Airport arrival, LocalDate date) {
        String jpqlQuery = "SELECT f FROM Flight f ";
        LinkedList<Object[]> params = new LinkedList<>();
        if (departure != null || arrival != null || date != null) {
            jpqlQuery += "WHERE ";
            if (departure != null) {
                jpqlQuery += "f.departure=:departure ";
                params.add(new Object[]{"departure", departure});
            }
            if (arrival != null) {
                if (departure != null) {
                    jpqlQuery += "AND ";
                }
                jpqlQuery += "f.arrival=:arrival ";
                params.add(new Object[]{"arrival", arrival});
            }
            if (date != null) {
                if (departure != null || arrival != null) {
                    jpqlQuery += "AND ";
                }
                jpqlQuery += "(DATE(f.departureTime)=DATE(:flightDate) OR DATE(f.arrivalTime)=DATE(:flightDate))";
                params.add(new Object[]{"flightDate", date});
            }
        }
        Query query = entityManager.createQuery(jpqlQuery);
        for (Object[] o :
                params) {
            query.setParameter((String) o[0], o[1]);
        }
        return query.getResultList();
    }
}

