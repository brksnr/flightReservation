package com.example.demo.repository;

import com.example.demo.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Long countByShipId(Long shipId);
    boolean existsBySeatNumber(String seatNumber);

    Optional<Seat> findBySeatNumberAndShipId(String seatNumber, Long shipId);
}
