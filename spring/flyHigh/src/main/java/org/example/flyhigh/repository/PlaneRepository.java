package org.example.flyhigh.repository;

import org.example.flyhigh.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}