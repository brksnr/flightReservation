package com.example.demo.repository;


import com.example.demo.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship,Long> {
    Optional<Ship> findByName(String name);
}
