package com.bank.bank_system.entity;

public enum CreditType {
    CONSUMER_CREDIT("Потребительский кредит"),
    MORTGAGE_CREDIT("Ипотечный кредит"),
    CAR_CREDIT("Автокредит"),
    BUSINESS_CREDIT("Кредит для бизнеса"),
    OTHER("Другой вид кредита");

    private final String description;

    CreditType(String description) {
        this.description = description;
    }
}
