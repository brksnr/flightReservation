package com.example.demo.repository;

import com.example.demo.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberReservationRepository extends JpaRepository<Travel, Long> {

    boolean existsByCreatedByIdAndShipIdAndDepartureDate(Long createdById, Long shipId, LocalDateTime departureDate);

}
