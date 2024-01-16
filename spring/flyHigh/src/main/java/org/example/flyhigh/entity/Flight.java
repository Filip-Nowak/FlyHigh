package org.example.flyhigh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    @ManyToOne
    private Airport departure;
    @ManyToOne
    private Airport arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    @ManyToOne
    private Plane plane;
    @OneToMany(mappedBy = "flight",fetch = FetchType.EAGER)
    private List<Seat> seats;
}
