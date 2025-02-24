package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "departure_planet_id", nullable = false)
    private Planet departurePlanet;

    @ManyToOne
    @JoinColumn(name = "arrival_planet_id", nullable = false)
    private Planet arrivalPlanet;

    @Column(name = "departure_date", nullable = false)
    private String departureDate;

    @Column(name = "arrival_date", nullable = false)
    private String arrivalDate;

    @Column(name = "price", nullable = false)
    private Double price;

}
