package org.example.flyhigh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.flyhigh.entity.user.Ticket;
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
    @ManyToOne
    private Ticket ticket;
    @ManyToOne
    private Flight flight;
    private double price;

}
