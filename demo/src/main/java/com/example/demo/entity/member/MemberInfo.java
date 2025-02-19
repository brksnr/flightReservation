package com.example.demo.entity.member;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "member_info", schema = "flight")
public class MemberInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "birth_date")
    private String birthdate;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @ElementCollection
    @CollectionTable(name = "member_allergy", schema = "flight", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "allergy")
    private List<String> allergy;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Member member;



}
