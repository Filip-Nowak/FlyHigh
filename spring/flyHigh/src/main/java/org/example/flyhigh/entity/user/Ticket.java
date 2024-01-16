package org.example.flyhigh.entity.user;

import jakarta.persistence.*;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Seat;

import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private UserProfile user;
    @OneToMany(mappedBy = "ticket")
    private List<Seat> seats;
    private double price;
}
