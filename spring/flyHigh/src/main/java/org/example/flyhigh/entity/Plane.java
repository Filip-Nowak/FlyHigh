package org.example.flyhigh.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.flyhigh.model.plane.PlaneInfoModel;

import java.util.LinkedList;
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
    public PlaneInfoModel toPlaneInfoModel(){
        return PlaneInfoModel.builder()
                .id(id)
                .model(type.getName())
                .build();
    }
    public static List<PlaneInfoModel> toPlaneInfoModelList(List<Plane> planes){
        List<PlaneInfoModel> models = new LinkedList<>();
        for (Plane plane : planes) {
            models.add(plane.toPlaneInfoModel());
        }
        return models;
    }
}
