package com.bank.bank_system.service;

import com.bank.bank_system.entity.Payment;
import com.bank.bank_system.entity.PaymentType;
import com.bank.bank_system.exception.InvalidPaymentException;
import com.bank.bank_system.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    PaymentRepository paymentRepository;

    private List<PaymentProcessor> paymentProcessors;

    public void mikePayment(Payment payment){
        validatePayment(payment);

        PaymentProcessor paymentProcessor = getPaymentProcessor(payment.getPaymentType());
        paymentProcessor.processorPayment(payment);

        paymentRepository.save(payment);
    }

    private PaymentProcessor getPaymentProcessor(PaymentType paymentType) {
        return paymentProcessors.stream()
                .filter(processor -> processor.supports(paymentType))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Unsupported payment type"));
    }

    private void validatePayment(Payment payment) {
        if ((payment == null) || (payment.getAmount() == null)){
            throw new InvalidPaymentException("Invalid payment details");
        }
    }


}
