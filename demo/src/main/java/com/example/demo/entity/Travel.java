package com.example.demo.entity;

import com.example.demo.entity.member.CreditCard;
import com.example.demo.entity.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "travel", schema = "flight")
public class Travel {

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

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Member createdBy;

    @ManyToMany
    @JoinTable(
            name = "travel_seat",
            joinColumns = @JoinColumn(name = "travel_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

}
