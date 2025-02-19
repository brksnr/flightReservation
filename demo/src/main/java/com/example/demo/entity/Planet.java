package com.example.demo.entity;


import jakarta.persistence.*;

import java.util.List;

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
    private List<Travel> departureTravels;

    @OneToMany(mappedBy = "arrivalPlanet")
    private List<Travel> arrivalTravels;
}
