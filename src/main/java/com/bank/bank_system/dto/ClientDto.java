package com.bank.bank_system.dto;

import com.bank.bank_system.entity.Account;
import lombok.Value;

import java.util.List;

@Value
public class ClientDto {
     Long id;
     String firstName;
     String lastName;
     String middleName;
     String email;
     String phoneNumber;
     String address;
     String password;

     List<Account> accounts;

}
