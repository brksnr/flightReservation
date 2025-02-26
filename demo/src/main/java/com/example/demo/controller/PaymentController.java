package com.example.demo.controller;

import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay/{memberId}/{travelId}/{creditCardId}")
    public ResponseEntity<Payment> processPayment(@PathVariable Long memberId,@PathVariable Long travelId,@PathVariable Long creditCardId){
        Payment payment = paymentService.processPayment(memberId, travelId, creditCardId);
        return ResponseEntity.ok(payment);
    }
}
