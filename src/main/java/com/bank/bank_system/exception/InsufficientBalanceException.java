package com.bank.bank_system.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(Long accountId, Double amount) {
        super("Insufficient balance in account " + accountId + " to withdraw " + amount);
    }

    public InsufficientBalanceException(String messages) {
        super(messages);
    }
}
