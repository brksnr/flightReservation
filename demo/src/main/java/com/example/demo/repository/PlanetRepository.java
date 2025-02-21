package com.example.demo.repository;

import com.example.demo.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

        Optional<Planet> findByName(String name);
        Optional<Planet> findByLocation(Integer location);
}
