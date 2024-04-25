package com.harshkumar.paymentservice.controllers;

import com.harshkumar.paymentservice.dtos.InitiatePaymentRequestDTO;
import com.harshkumar.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    PaymentController(PaymentService paymentService) {
        this.paymentService=paymentService;
    }
    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDTO initiatePaymentRequestDTO) throws RazorpayException, StripeException {
        return paymentService.initiatePayment(initiatePaymentRequestDTO.getOrderId(),
                initiatePaymentRequestDTO.getEmail()
        );
//        return "hi";
    }
}
