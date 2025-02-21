package com.example.demo.controller;

import com.example.demo.entity.Ship;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ship")
public class ShipController {

    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addShip(@RequestBody Ship ship){
        shipService.addShip(ship);
        return ResponseEntity.ok("Gemi Başarıyla eklendi!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShip(@PathVariable Long id){
        shipService.deleteShip(id);
        return ResponseEntity.ok("Gemi başarıyla silindi!");
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Ship>> getAllShip(){
        List<Ship> allShip = shipService.getAllShip();
        return ResponseEntity.ok(allShip);
    }
}
