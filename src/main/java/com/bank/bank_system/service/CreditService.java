package com.bank.bank_system.service;

import com.bank.bank_system.entity.Credit;
import com.bank.bank_system.entity.CreditType;
import com.bank.bank_system.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    CreditRepository creditRepository;

    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    public Credit getCreditById(Long id) {
        return creditRepository.findById(id).orElseThrow();
    }

    public Credit createCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public Credit updateCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    public List<Credit> getCreditsByType(CreditType creditType) {
        return creditRepository.findByCreditType(creditType);
    }

    public double calculateMonthlyPayment(Credit credit) {
        double amount = credit.getAmount();
        double interestRate = credit.getInterestRate() / 100 / 12;
        int term = credit.getTerm();

        return amount * interestRate * Math.pow(1 + interestRate, term) / (Math.pow(1 + interestRate, term) - 1);
    }
}
