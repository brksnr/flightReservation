package com.example.demo.entity.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "credit_card", schema = "flight")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "cardholder_name")
    private String cardholderName;

    @NotNull
    @Size(min = 16,max = 16, message = "Kart numarası 16 haneden oluşmalıdır!")
    @Column(name = "card_number")
    private String cardNumber;

    @NotNull
    @Size(min = 5,max = 5, message = "Geçerli bir tarih giriniz!")
    @Column(name = "expiration_date")
    private String expirationDate;

    @NotNull
    @Size(min = 3,max = 3, message = "CVC 3 haneden oluşmalıdır!")
    @Column(name = "cvc")
    private String cvc;

    @ManyToOne
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo;
}
