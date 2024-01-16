package org.example.flyhigh.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private PlaneType type;
    @OneToMany(mappedBy = "plane",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Flight> flights;
    public void addFlight(Flight flight){
        flights.add(flight);
    }
    @OneToMany(mappedBy = "plane", fetch = FetchType.EAGER)
    private List<PlaneAvailability> planeAvailabilities;
    public void addPlaneAvailability(PlaneAvailability planeAvailability){
        if(planeAvailabilities==null)
            planeAvailabilities=new ArrayList<>();
        System.out.println("supa");
        planeAvailabilities.add(planeAvailability);
    }
}
