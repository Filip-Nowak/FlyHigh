package org.example.flyhigh.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @ToString.Exclude
    private Ticket ticket;
    @ToString.Exclude
    @ManyToOne
    private Flight flight;
}
