package com.harshkumar.paymentservice.paymentGateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    public String generatePaymentLink(Long orderId, String email) throws RazorpayException, StripeException;
}
