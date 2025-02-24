package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "create_flight", schema = "flight")
public class CreateFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;


    @ManyToOne
    @JoinColumn(name = "departure_planet_id", nullable = false)
    private Planet departurePlanet;

    @ManyToOne
    @JoinColumn(name = "arrival_planet_id", nullable = false)
    private Planet arrivalPlanet;

    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(name = "price", nullable = false)
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Planet getDeparturePlanet() {
        return departurePlanet;
    }

    public void setDeparturePlanet(Planet departurePlanet) {
        this.departurePlanet = departurePlanet;
    }

    public Planet getArrivalPlanet() {
        return arrivalPlanet;
    }

    public void setArrivalPlanet(Planet arrivalPlanet) {
        this.arrivalPlanet = arrivalPlanet;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
