package org.example.flyhigh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.flyhigh.enums.SeatClass;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int seatNumber;
    private SeatClass seatClass;
    private boolean isAvailable;
    @ManyToOne
    private Flight flight;

}
