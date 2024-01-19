package com.springboot.ecommers.entity;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class PaymentInfo {
    @Column(name = "cardholder_name")
    private String cardHolderName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "ccv")
    private String ccv;

}
