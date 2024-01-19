package org.example.flyhigh.model.flight;

import lombok.Builder;
import lombok.Data;
import org.example.flyhigh.enums.SeatClass;
@Data
@Builder
public class AddedSeatModel {
    private long id;
    private int seatNumber;
    private SeatClass seatClass;
}
