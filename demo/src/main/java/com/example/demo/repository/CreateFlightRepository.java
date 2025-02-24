package com.example.demo.repository;

import com.example.demo.entity.CreateFlight;
import com.example.demo.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


public interface CreateFlightRepository extends JpaRepository<CreateFlight, Long> {
    boolean existsByShipAndDepartureDateTime(Ship ship, LocalDateTime departureDateTime);
}
