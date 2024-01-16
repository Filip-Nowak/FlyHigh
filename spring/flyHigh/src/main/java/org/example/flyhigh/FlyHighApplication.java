package org.example.flyhigh;

import org.example.flyhigh.entity.*;
import org.example.flyhigh.entity.user.Role;
import org.example.flyhigh.entity.user.Ticket;
import org.example.flyhigh.entity.user.User;
import org.example.flyhigh.entity.user.UserProfile;
import org.example.flyhigh.repository.PlaneTypeRepository;
import org.example.flyhigh.repository.RoleRepository;
import org.example.flyhigh.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
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
    CommandLineRunner commandLineRunner(TicketService ticketService,RoleRepository roleRepository,AirportService airportService, PlaneService planeService, PlaneTypeRepository planeTypeRepository, FlightService flightService, UserService userService) {
        return args -> {
            loadAirports(airportService);
            loadPlanes(planeService, planeTypeRepository);
            loadUsers(userService,roleRepository);
            loadFlights(flightService, airportService, planeService);
            loadTickets(ticketService,userService, flightService);
            //Plane plane = planeService.getAvailablePlane(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        };
    }

    private void loadTickets(TicketService ticketService,UserService userService, FlightService flightService) {
        User user = (User) userService.loadUserByUsername("user");
        Flight flight = flightService.getAllFlights().get(0);
        List<Integer> seatNumbers = new LinkedList<>();
        seatNumbers.add(1);
        seatNumbers.add(2);
        seatNumbers.add(3);
        Ticket ticket=ticketService.saveTicket(seatNumbers,user.getUserProfile(),flight);

    }

    private void loadUsers(UserService userService, RoleRepository roleRepository){
        roleRepository.save(Role.builder()
                .name("USER")
                .build());
        User user=User.builder()
                .username("user")
                .password("user")
                .build();
        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .build();
        user.setUserProfile(userProfile);
        userService.saveUser(user);
    }

    private void loadFlights(FlightService flightService, AirportService airportService, PlaneService planeService) {
        Random random = new Random();
        List<Airport> airports = airportService.getAllAirports();
        List<Plane> planes = planeService.getAllPlanes();
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            Airport departure = airports.get(random.nextInt(airports.size()));
            Airport arrival = airports.get(random.nextInt(airports.size()));
            while (departure.equals(arrival)) {
                arrival = airports.get(random.nextInt(airports.size()));
            }
            LocalDateTime departureTime = LocalDateTime.now().plusDays(random.nextInt(100));
            LocalDateTime arrivalTime = departureTime.plusHours(random.nextInt(10));
            Plane plane = planes.get(random.nextInt(planes.size()));
            Flight flight = Flight.builder()
                    .departure(departure)
                    .arrival(arrival)
                    .departureTime(departureTime)
                    .arrivalTime(arrivalTime)
                    .plane(plane)
                    .build();
            List<Seat> seats = new LinkedList<>();
            flight.setSeats(seats);
            flightService.addFlight(flight);
        }
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
