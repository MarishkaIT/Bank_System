package com.bank.bank_system.entity;

import lombok.Data;

public enum PaymentType {
    CARD("Карта"),
    TRANSFER("Перевод"),
    CASH("Наличные"),
    ONLINE_BANING("Интернет-банк");

    private final String description;

    PaymentType(String description) {
        this.description = description;
    }
}
