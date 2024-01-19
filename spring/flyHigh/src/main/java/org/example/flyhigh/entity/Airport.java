package org.example.flyhigh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.flyhigh.model.airport.AddedAirportModel;
import org.example.flyhigh.model.airport.AirportInfoModel;

import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String city;
    public AirportInfoModel toAirportInfoModel(){
        return AirportInfoModel.builder()
                .city(city)
                .build();
    }
    public AddedAirportModel toAddedAirportModel(){
        return AddedAirportModel.builder()
                .id(id)
                .city(city)
                .build();
    }
    public static List<AirportInfoModel> toAirportInfoModels(List<Airport> airports){
        List<AirportInfoModel> airportInfoModels = new LinkedList<>();
        for(Airport airport:airports){
            airportInfoModels.add(airport.toAirportInfoModel());

        }
        return airportInfoModels;
    }
}
