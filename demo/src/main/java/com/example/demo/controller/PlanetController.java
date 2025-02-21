package com.example.demo.controller;

import com.example.demo.entity.Planet;
import com.example.demo.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
public class PlanetController {

    private final PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPlanet(@RequestBody Planet planet) {
        planetService.addPlanet(planet);
        return ResponseEntity.ok("Gezegen başarıyla eklendi!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
        return ResponseEntity.ok("Gezegen başarıyla silindi!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Planet>> getAllPlanets() {
        List<Planet> planets = planetService.getAllPlanets();
        return ResponseEntity.ok(planets);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Planet> getPlanetByName(@PathVariable String name) {
        Planet planet = planetService.getPlanetByName(name);
        return ResponseEntity.ok(planet);
    }
}
