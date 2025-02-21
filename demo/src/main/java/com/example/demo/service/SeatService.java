package com.example.demo.service;

import com.example.demo.entity.Seat;
import com.example.demo.entity.Ship;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final ShipRepository shipRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository, ShipRepository shipRepository) {
        this.seatRepository = seatRepository;
        this.shipRepository = shipRepository;
    }

    public void addSeatToShip(Long shipId, Seat seat) {

        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new ApiException("Gemi bulunamadı!", HttpStatus.NOT_FOUND));

        Long existingSeatsCount = seatRepository.countByShipId(shipId);
        if (seatRepository.existsBySeatNumber(seat.getSeatNumber())) {
            throw new ApiException("Bu koltuk numarası zaten mevcut!", HttpStatus.BAD_REQUEST);
        }
        if (existingSeatsCount >= ship.getCapacity()) {
            throw new ApiException("Bu gemiye maksimum koltuk sayısı eklenmiş!", HttpStatus.BAD_REQUEST);
        }

        seat.setShip(ship);
        seat.setIsAvailable(true);
        seatRepository.save(seat);
    }

}
