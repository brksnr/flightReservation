package com.example.demo.service;

import com.example.demo.dto.CreditCardRequest;
import com.example.demo.entity.member.CreditCard;
import com.example.demo.entity.member.Member;
import com.example.demo.entity.member.MemberInfo;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private final MemberRepository memberRepository;
    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(MemberRepository memberRepository, CreditCardRepository creditCardRepository) {
        this.memberRepository = memberRepository;
        this.creditCardRepository = creditCardRepository;
    }


    public void saveCreditCard(Long memberId, CreditCardRequest request){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND));

        if (creditCardRepository.existsByCardNumber(request.cardNumber())) {
            throw new ApiException("Bu kart numarası daha önce eklenmiş!", HttpStatus.BAD_REQUEST);
        }

        CreditCard newCreditCard = new CreditCard();
        newCreditCard.setName(request.name());
        newCreditCard.setCardholderName(request.cardholderName());
        newCreditCard.setCardNumber(request.cardNumber());
        newCreditCard.setExpirationDate(request.expirationDate());
        newCreditCard.setCvc(request.cvc());
        newCreditCard.setMemberInfo(member.getMemberInfo());

        creditCardRepository.save(newCreditCard);
    }

    public void deleteCreditCard(Long memberId, Long cardId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND));

        CreditCard creditCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new ApiException("Kredi kartı bulunamadı!", HttpStatus.NOT_FOUND));

        if (!creditCard.getMemberInfo().equals(member.getMemberInfo())) {
            throw new ApiException("Bu kredi kartını silme yetkiniz yok!", HttpStatus.FORBIDDEN);
        }

        creditCardRepository.delete(creditCard);
    }

}
