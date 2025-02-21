package com.example.demo.service;

import com.example.demo.entity.Planet;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public void addPlanet(Planet planet) {
        if (planetRepository.findByName(planet.getName()).isPresent()) {
            throw new ApiException("Bu gezegen zaten mevcut!", HttpStatus.BAD_REQUEST);
        }
        if(planetRepository.findByLocation(planet.getLocation()).isPresent()){
            throw new ApiException("Gezegenlerin konumu aynı olamaz!", HttpStatus.BAD_REQUEST);
        }
         planetRepository.save(planet);
    }

    public void deletePlanet(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new ApiException("Gezegen bulunamadı!", HttpStatus.NOT_FOUND));

        planetRepository.delete(planet);
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Planet getPlanetByName(String name) {
        return planetRepository.findByName(name)
                .orElseThrow(() -> {
                    throw new ApiException("Bu isme göre bir gezegen bulunamadı!", HttpStatus.NOT_FOUND);
                });
    }
}
