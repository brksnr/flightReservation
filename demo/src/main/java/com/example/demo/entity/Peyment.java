package com.example.demo.entity;

import com.example.demo.entity.member.CreditCard;
import com.example.demo.entity.member.Member;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "payment", schema = "flight")
public class Peyment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "travel_id", nullable = false)
    private Travel travel;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    private Timestamp paymentDate;
}

