package org.example.flyhigh.rest;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.Plane;
import org.example.flyhigh.entity.PlaneType;
import org.example.flyhigh.model.plane.AddPlaneRequest;
import org.example.flyhigh.model.plane.PlaneInfoModel;
import org.example.flyhigh.service.PlaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/")
public class PlaneRestController {
    private final PlaneService planeService;

    @GetMapping("planes")
    public ResponseEntity<List<PlaneInfoModel>> getPanes() {
        List<Plane> planes = planeService.getAllPlanes();
        List<PlaneInfoModel> models = new LinkedList<>();
        for (Plane plane : planes) {
            models.add(PlaneInfoModel.builder()
                    .id(plane.getId())
                    .model(plane.getType().getName())
                    .build());
        }
        return ResponseEntity.ok(models);
    }
    @PostMapping("plane")
    public ResponseEntity<Plane> addPlane(@RequestBody AddPlaneRequest planeRequest){
        System.out.println("dziala");
        Optional<PlaneType> optionalPlaneType= planeService.getPlaneTypeByName(planeRequest.getModel());
        if(optionalPlaneType.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Plane planeToAdd = Plane.builder()
                .type(optionalPlaneType.get())
                .build();
        Plane plane = planeService.addPlane(planeToAdd);
        return ResponseEntity.ok(plane);
    }
}

