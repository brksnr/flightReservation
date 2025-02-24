package com.example.demo.controller;

import com.example.demo.entity.CreateFlight;
import com.example.demo.exceptions.ApiException;
import com.example.demo.service.CreateFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class CreateFlightController {

    private final CreateFlightService createFlightService;

    @Autowired
    public CreateFlightController(CreateFlightService createFlightService) {
        this.createFlightService = createFlightService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> createFlight(@RequestBody CreateFlight createFlight) {
        try {
            createFlightService.createFlight(createFlight);
            return ResponseEntity.ok("Uçuş başarıyla eklendi!");
        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hata: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sunucu hatası!");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id){
        createFlightService.deleteFlight(id);
        return ResponseEntity.ok("Uçuş silindi!");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CreateFlight> getFlight(@PathVariable Long id){
       CreateFlight flight = createFlightService.getFlightById(id);

        return ResponseEntity.ok(flight);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CreateFlight>> getAllFlights(){
        List<CreateFlight> flights = createFlightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody CreateFlight updatedFlight) {
        try {
            createFlightService.updateFlight(id, updatedFlight);
            return ResponseEntity.ok("Uçuş başarıyla güncellendi!");
        } catch (ApiException e) {
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        } catch (Exception e) {
            return new ResponseEntity<>("Bir hata oluştu!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
