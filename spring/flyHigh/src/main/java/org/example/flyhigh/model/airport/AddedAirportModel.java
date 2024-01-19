package org.example.flyhigh.model.airport;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddedAirportModel {
    private long id;
    private String city;
}
