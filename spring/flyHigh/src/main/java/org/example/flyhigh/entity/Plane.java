package org.example.flyhigh.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private PlaneType type;
    @OneToMany(mappedBy = "plane")
    private List<Flight> flights;
    @OneToMany(mappedBy = "plane")
    private List<Seat> seats;
}
