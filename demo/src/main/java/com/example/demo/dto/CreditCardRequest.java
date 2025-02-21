package com.example.demo.dto;

public record CreditCardRequest(String name,String cardholderName, String cardNumber, String expirationDate, String cvc) {
}
