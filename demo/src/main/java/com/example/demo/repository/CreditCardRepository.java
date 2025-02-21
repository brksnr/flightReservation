package com.example.demo.repository;

import com.example.demo.entity.member.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

        CreditCard findByMemberInfoId(Long memberInfoId);
        boolean existsByCardNumber(String cardNumber);
}
