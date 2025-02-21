package com.example.demo.controller;

import com.example.demo.entity.Seat;
import com.example.demo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }


    @PostMapping("/add/{shipId}")
    public ResponseEntity<String> addSeatToShip(@PathVariable Long shipId, @RequestBody Seat seat){
        seatService.addSeatToShip(shipId, seat);
        return ResponseEntity.ok("Koltuk başarıyla eklendi!");
    }
}
