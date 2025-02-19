package com.example.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ship",schema = "flight")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Max(value = 5, message = "Gemi için maksimum koltuk sayısına ulaşıldı. Başka gemi bakınız!")
    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "speed")
    private Long speed;

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    @OneToMany(mappedBy = "ship")
    private List<Travel> travels;

}
