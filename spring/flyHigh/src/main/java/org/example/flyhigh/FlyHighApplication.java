package org.example.flyhigh;

import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.PlaneType;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.enums.SeatClass;
import org.example.flyhigh.repository.PlaneTypeRepository;
import org.example.flyhigh.service.AirportService;
import org.example.flyhigh.service.PlaneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.example.flyhigh.constants.Constants.*;

@SpringBootApplication
public class FlyHighApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyHighApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AirportService airportService, PlaneService planeService, PlaneTypeRepository planeTypeRepository) {
        return args -> {
            loadAirports(airportService);
            loadPlanes(planeService, planeTypeRepository);
        };
    }

    private void loadPlanes(PlaneService planeService, PlaneTypeRepository planeTypeRepository) {

        for (int i = 0; i < PLANE_TYPES.length; i++) {
            planeTypeRepository.save(PlaneType.builder()
                    .name(PLANE_TYPES[i])
                    .businessCapacity(PLANE_TYPE_BUSINESS_CAPACITY.get(PLANE_TYPES[i]))
                    .economyCapacity(PLANE_TYPE_ECONOMIC_CAPACITY.get(PLANE_TYPES[i]))
                    .firstCapacity(PLANE_TYPE_FIRST_CAPACITY.get(PLANE_TYPES[i])).build());
        }

        Random random = new Random();

        for (int i = 0; i < 200; i++) {
            Plane plane = new Plane();
            PlaneType randomPlaneType = planeTypeRepository.findById(random.nextLong(PLANE_TYPES.length) + 1).get();
            planeService.addPlane(Plane.builder()
                    .type(randomPlaneType)
                    .build());
        }

    }

    private void loadAirports(AirportService airportService) {
        for (String city : CITIES) {
            airportService.addAirport(new Airport(0, city));
        }
    }
}
