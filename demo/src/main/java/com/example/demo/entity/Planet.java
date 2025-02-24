package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "planet", schema = "flight")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private PlanetType type;

    @Column(name = "location")
    private Integer location;

    @OneToMany(mappedBy = "departurePlanet")
    @JsonBackReference
    private List<Travel> departureTravels;

    @OneToMany(mappedBy = "arrivalPlanet")
    @JsonBackReference
    private List<Travel> arrivalTravels;

    @OneToMany(mappedBy = "departurePlanet")
    @JsonBackReference
    private List<CreateFlight> departureCreateFlights;

    @OneToMany(mappedBy = "arrivalPlanet")
    @JsonBackReference
    private List<CreateFlight> arrivalCreateFlights;





    public List<CreateFlight> getDepartureCreateFlights() {
        return departureCreateFlights;
    }

    public void setDepartureCreateFlights(List<CreateFlight> departureCreateFlights) {
        this.departureCreateFlights = departureCreateFlights;
    }

    public List<CreateFlight> getArrivalCreateFlights() {
        return arrivalCreateFlights;
    }

    public void setArrivalCreateFlights(List<CreateFlight> arrivalCreateFlights) {
        this.arrivalCreateFlights = arrivalCreateFlights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetType getType() {
        return type;
    }

    public void setType(PlanetType type) {
        this.type = type;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public List<Travel> getDepartureTravels() {
        return departureTravels;
    }

    public void setDepartureTravels(List<Travel> departureTravels) {
        this.departureTravels = departureTravels;
    }

    public List<Travel> getArrivalTravels() {
        return arrivalTravels;
    }

    public void setArrivalTravels(List<Travel> arrivalTravels) {
        this.arrivalTravels = arrivalTravels;
    }
}
