package com.bank.bank_system.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client " + id + " not found!");
    }

    public ClientNotFoundException(String  message) {
        super(message);
    }


}
