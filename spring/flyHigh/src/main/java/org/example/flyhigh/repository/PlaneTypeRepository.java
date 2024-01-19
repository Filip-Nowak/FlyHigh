package org.example.flyhigh.repository;

import org.example.flyhigh.entity.PlaneType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneTypeRepository extends JpaRepository<PlaneType, Long> {
    Optional<PlaneType> findByName(String name);
}
