package org.example.flyhigh.model.plane;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class AddPlaneRequest {
    @NotNull
    private String model;
}
