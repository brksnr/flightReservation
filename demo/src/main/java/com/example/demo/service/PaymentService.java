package com.example.demo.service;

import com.example.demo.entity.Payment;
import com.example.demo.entity.Travel;
import com.example.demo.entity.member.CreditCard;
import com.example.demo.entity.member.Member;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.TravelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TravelRepository travelRepository;
    private final CreditCardRepository creditCardRepository;
    private final MemberRepository memberRepository;

    public PaymentService(PaymentRepository paymentRepository, TravelRepository travelRepository,
                          CreditCardRepository creditCardRepository, MemberRepository memberRepository) {
        this.paymentRepository = paymentRepository;
        this.travelRepository = travelRepository;
        this.creditCardRepository = creditCardRepository;
        this.memberRepository = memberRepository;
    }

    public Payment processPayment(Long memberId, Long travelId, Long creditCardId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND));

        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new ApiException("Rezervasyon bulunamadı!", HttpStatus.NOT_FOUND));

        CreditCard creditCard = creditCardRepository.findById(creditCardId)
                .orElseThrow(() -> new ApiException("Kredi kartı bulunamadı!", HttpStatus.NOT_FOUND));

        if (!travel.getCreatedBy().getId().equals(memberId)) {
            throw new ApiException("Bu rezervasyon size ait değil!", HttpStatus.FORBIDDEN);
        }

        Payment payment = new Payment();
        payment.setMember(member);
        payment.setTravel(travel);
        payment.setCreditCard(creditCard);
        payment.setAmount(travel.getPrice());
        payment.setPaymentDate(Timestamp.from(Instant.now()));

        return paymentRepository.save(payment);
    }
}
