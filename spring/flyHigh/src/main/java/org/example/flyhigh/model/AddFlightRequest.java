package org.example.flyhigh.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddFlightRequest {
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private long planeId;
    private double economyPrice;
    private double businessPrice;
    private double firstPrice;
}
