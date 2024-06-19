package com.bank.bank_system.exception;

import com.bank.bank_system.entity.Account;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(Long accountId, BigDecimal amount) {
        super("Insufficient balance in account " + accountId + " to withdraw " + amount);
    }
}
