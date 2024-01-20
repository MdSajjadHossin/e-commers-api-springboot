package com.springboot.ecommers.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PaymentDetails {

    private String paymentMethod;
    private String status;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;
}
