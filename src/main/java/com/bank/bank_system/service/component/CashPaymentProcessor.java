package com.bank.bank_system.service.component;

import com.bank.bank_system.entity.Payment;
import com.bank.bank_system.entity.PaymentType;
import com.bank.bank_system.service.PaymentProcessor;
import org.springframework.stereotype.Component;

@Component
public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean supports(PaymentType paymentType) {
        return paymentType == PaymentType.CASH;
    }

    @Override
    public void processorPayment(Payment payment) {
        System.out.println("Processing cash payment...");
    }
}
