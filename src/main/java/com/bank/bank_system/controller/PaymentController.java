package com.bank.bank_system.controller;

import com.bank.bank_system.entity.Payment;
import com.bank.bank_system.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> makePayment(@RequestBody @Valid Payment payment) {
        try {
            paymentService.mikePayment(payment);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error making payment: " + e.getMessage());
        }
    }

}
