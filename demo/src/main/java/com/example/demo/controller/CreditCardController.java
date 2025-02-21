package com.example.demo.controller;


import com.example.demo.dto.CreditCardRequest;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/save/{memberId}")
    public ResponseEntity<String> saveCreditCard(@PathVariable Long memberId,@RequestBody CreditCardRequest request){
        creditCardService.saveCreditCard(memberId, request);
        return ResponseEntity.ok("Kredi kartı başarıyla eklendi!");
    }

    @DeleteMapping("/delete/{memberId}/{cardId}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable Long memberId, @PathVariable Long cardId){
        creditCardService.deleteCreditCard(memberId,cardId);
        return ResponseEntity.ok("Kredi karktı başarıyla silindi!");
    }
}
