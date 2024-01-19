package org.example.flyhigh.model.plane;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaneInfoModel {
    private long id;
    private String model;
}
