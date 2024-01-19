package org.example.flyhigh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.flyhigh.model.flight.AddedFlightModel;
import org.example.flyhigh.model.flight.AddedSeatModel;
import org.example.flyhigh.model.flight.FlightInfoModel;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @OneToMany(mappedBy = "flight",fetch = FetchType.EAGER)
    private List<Seat> seats;
    private double economyPrice;
    private double businessPrice;
    private double firstPrice;
    public FlightInfoModel toFlightInfoModel(){
        return FlightInfoModel.builder()
                .departure(departure.getCity())
                .arrival(arrival.getCity())
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .economyPrice(economyPrice)
                .businessPrice(businessPrice)
                .firstPrice(firstPrice)
                .build();
    }
    public AddedFlightModel toAddedFlightModel(){
        return AddedFlightModel.builder()
                .id(id)
                .departure(departure.getCity())
                .arrival(arrival.getCity())
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .economyPrice(economyPrice)
                .businessPrice(businessPrice)
                .firstPrice(firstPrice)
                .seats(getAddedSeatModels())
                .planeId(plane.getId())
                .build();
    }
    public static List<FlightInfoModel> toFlightInfoModelList(List<Flight> flights){
        List<FlightInfoModel> models=new LinkedList<>();
        for(Flight flight:flights){
            models.add(flight.toFlightInfoModel());
        }
        return models;
    }
    private List<AddedSeatModel> getAddedSeatModels(){
        List<AddedSeatModel> models=new LinkedList<>();
        for(Seat seat:seats){
            models.add(AddedSeatModel.builder()
                    .id(seat.getId())
                    .seatClass(seat.getSeatClass())
                    .seatNumber(seat.getSeatNumber())
                    .build());
        }
        return models;
    }
}
