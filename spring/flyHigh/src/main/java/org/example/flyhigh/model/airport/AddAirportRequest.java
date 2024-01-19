package org.example.flyhigh.model.airport;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddAirportRequest {
    @NotNull
    private String city;
}
