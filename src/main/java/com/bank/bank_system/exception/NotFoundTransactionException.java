package com.bank.bank_system.exception;

public class NotFoundTransactionException extends RuntimeException {
    public NotFoundTransactionException(String message) {
        super(message);
    }
}
