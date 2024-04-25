package com.harshkumar.paymentservice.paymentGateways;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//import org.springframework.validation.annotation.Validated;

@Component
//@Primary
public class RazorpayPaymentGatweway implements PaymentGateway{
    @Value("${razorpay.key.id}")
    private String razorPayId;
    @Value("${razorpay.key.secret}")
    private String razorPaySecret;

    public String generatePaymentLink(Long orderId, String email) throws RazorpayException {

        StringBuilder refrenceNumber = new StringBuilder();
        while(orderId>0){
            refrenceNumber.append(orderId%10);
            orderId=orderId/10;
        }

        RazorpayClient razorpay = new RazorpayClient(razorPayId, razorPaySecret);
        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount",100000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1719963943);
        paymentLinkRequest.put("reference_id",refrenceNumber.toString());
        paymentLinkRequest.put("description","Payment for Demo Purpose");
        JSONObject customer = new JSONObject();
        customer.put("name","+919142840799");
        customer.put("contact","Harsh Kumar");
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://github.com/harsh-kumar-patwa");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
