package org.example.flyhigh.model.airport;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportInfoModel {
    private String city;
}
