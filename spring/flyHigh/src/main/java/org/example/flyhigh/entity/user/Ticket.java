package org.example.flyhigh.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Seat;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private UserProfile user;
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Seat> seats;
    private double price;
}
