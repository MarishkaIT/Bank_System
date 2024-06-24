package com.bank.bank_system.dto;

import com.bank.bank_system.entity.AccountType;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountDto {

    Long id;
    String clientId;
    String accountNumber;
    BigDecimal balance;
    AccountType accountType;
}
