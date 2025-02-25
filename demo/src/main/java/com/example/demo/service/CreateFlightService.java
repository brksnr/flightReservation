package com.example.demo.service;

import com.example.demo.entity.CreateFlight;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.CreateFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateFlightService {

    private final CreateFlightRepository createFlightRepository;

    @Autowired
    public CreateFlightService(CreateFlightRepository createFlightRepository) {
        this.createFlightRepository = createFlightRepository;
    }

    public void createFlight(CreateFlight createFlight) {
        boolean flightExists = createFlightRepository.existsByShipAndDepartureDateTime(createFlight.getShip(), createFlight.getDepartureDateTime());
        if (flightExists) {
            throw new ApiException("Bu gemi ile aynı tarihte bir uçuş zaten var!", HttpStatus.BAD_REQUEST);
        }

        createFlightRepository.save(createFlight);
    }

    public List<CreateFlight> getAllFlights() {
        return createFlightRepository.findAll();
    }

    public CreateFlight getFlightById(Long id) {
        return createFlightRepository.findById(id)
                .orElseThrow(() -> new ApiException("Böyle bir uçuş bulunamadı!", HttpStatus.NOT_FOUND));
    }

    public CreateFlight updateFlight(Long id, CreateFlight updatedFlight) {

        CreateFlight existingFlight = createFlightRepository.findById(id)
                .orElseThrow(() -> new ApiException("Böyle bir uçuş bulunamadı!", HttpStatus.NOT_FOUND));

        boolean flightExists = createFlightRepository.existsByShipAndDepartureDateTime(updatedFlight.getShip(), updatedFlight.getDepartureDateTime());
        if (flightExists) {
            throw new ApiException("Bu gemi ile aynı tarihte bir uçuş zaten var!", HttpStatus.BAD_REQUEST);
        }

        existingFlight.setShip(updatedFlight.getShip());
        existingFlight.setDeparturePlanet(updatedFlight.getDeparturePlanet());
        existingFlight.setArrivalPlanet(updatedFlight.getArrivalPlanet());
        existingFlight.setDepartureDateTime(updatedFlight.getDepartureDateTime());
        existingFlight.setArrivalDateTime(updatedFlight.getArrivalDateTime());
        existingFlight.setPrice(updatedFlight.getPrice());

        return createFlightRepository.save(existingFlight);
    }

    public void deleteFlight(Long id) {
        CreateFlight flight = createFlightRepository.findById(id)
                .orElseThrow(() -> new ApiException("Böyle bir uçuş bulunamadı!", HttpStatus.NOT_FOUND));
        createFlightRepository.delete(flight);
    }
}
