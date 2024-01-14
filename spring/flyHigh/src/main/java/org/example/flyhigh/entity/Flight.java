package org.example.flyhigh.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    @ManyToOne
    private Airport departure;
    @ManyToOne
    private Airport arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    @ManyToOne
    private Plane plane;

}
