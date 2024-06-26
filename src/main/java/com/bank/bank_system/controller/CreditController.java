package com.bank.bank_system.controller;

import com.bank.bank_system.entity.Credit;
import com.bank.bank_system.entity.CreditType;
import com.bank.bank_system.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.CollectionTable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    CreditService creditService;

    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        List<Credit> credits = creditService.getAllCredits();
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Optional<Long> id) {
        if (!id.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        Credit credit = creditService.getCreditById(id.get());
        return ResponseEntity.ok(credit);
    }

    @PostMapping
    public ResponseEntity<Credit> createCredit(@RequestBody Credit credit) {
        Credit credit1 = creditService.createCredit(credit);
        return ResponseEntity.ok(credit1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credit> updateCredit(@PathVariable Long id, @RequestBody Credit credit) {
        if (!id.equals(credit.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID in path variable does not match ID in request body");
        }
        Credit credit1 = creditService.updateCredit(credit);
        credit1.setId(id);
        return ResponseEntity.ok(credit1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Credit>> getCreditsByType(@PathVariable CreditType type) {
        List<Credit> credits = creditService.getCreditsByType(type);
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/{id}/monthly-paymen")
    public ResponseEntity<Double> calculateMonthlyPayment(@PathVariable Long id) {
        Credit credit = creditService.getCreditById(id);
        if (credit != null) {
            double monthlyPayment = creditService.calculateMonthlyPayment(credit);
            return ResponseEntity.ok(monthlyPayment);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
