package org.example.flyhigh.entity;

import jakarta.persistence.*;
import org.example.flyhigh.enums.SeatClass;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int seatNumber;
    private SeatClass seatClass;
    private boolean isAvailable;
    @ManyToOne
    private Plane plane;

}
