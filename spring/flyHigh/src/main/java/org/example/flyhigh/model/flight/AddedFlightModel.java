package org.example.flyhigh.model.flight;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class AddedFlightModel {
    private long id;
    private String departure;
    private String arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private long planeId;
    private double economyPrice;
    private double businessPrice;
    private double firstPrice;
    private List<AddedSeatModel> seats;

}
