package org.example.flyhigh.model.flight;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class FlightInfoModel {
    private String departure;
    private String arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double economyPrice;
    private double businessPrice;
    private double firstPrice;
}
