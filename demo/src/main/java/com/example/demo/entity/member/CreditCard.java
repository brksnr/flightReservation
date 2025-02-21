package com.example.demo.entity.member;

import com.example.demo.entity.Peyment;
import com.example.demo.entity.Travel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "cardholder_name")
    private String cardholderName;

    @NotNull
    @Size(min = 19,max = 19, message = "Kart numarası 16 haneden oluşmalıdır!")
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
    @JsonBackReference
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo;

    @OneToMany(mappedBy = "creditCard")
    private List<Travel> travels;

    @OneToMany(mappedBy = "creditCard")
    private List<Peyment> payments;

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

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }

    public List<Peyment> getPayments() {
        return payments;
    }

    public void setPayments(List<Peyment> payments) {
        this.payments = payments;
    }
}
