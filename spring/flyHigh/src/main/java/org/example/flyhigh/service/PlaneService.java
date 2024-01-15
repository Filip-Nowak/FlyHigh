package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.repository.PlaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaneService {
    private PlaneRepository planeRepository;
    public Plane addPlane(Plane plane) {
        return planeRepository.save(plane);
    }
    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }
}
