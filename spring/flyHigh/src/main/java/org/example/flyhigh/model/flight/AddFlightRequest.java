package org.example.flyhigh.model.flight;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddFlightRequest {
    @NotNull
    private String departure;
    @NotNull
    private String arrival;
    @NotNull
    private String departureTime;
    @NotNull
    private String arrivalTime;
    @NotNull
    private long planeId;
    @NotNull
    private double economyPrice;
    @NotNull
    private double businessPrice;
    @NotNull
    private double firstPrice;
}
