package com.example.demo.entity;

import com.example.demo.entity.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seat", schema = "flight")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    @ManyToMany(mappedBy = "seats")
    private List<Member> members;
}

