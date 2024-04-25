package com.harshkumar.paymentservice.services;

import com.harshkumar.paymentservice.paymentGateways.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;
    PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway=paymentGateway;
    }
    public String initiatePayment(Long orderId,String email) throws RazorpayException, StripeException {
        return paymentGateway.generatePaymentLink(orderId,email);
    }
}
