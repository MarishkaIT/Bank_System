package com.bank.bank_system.service;

import com.bank.bank_system.entity.Payment;
import com.bank.bank_system.entity.PaymentType;
import lombok.Data;


public interface PaymentProcessor {

    boolean supports(PaymentType paymentType);
    void processorPayment(Payment payment);
}
