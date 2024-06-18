package com.bank.bank_system.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    private String phoneNumber;

    private String address;

    private String password;

    @OneToMany
    private List<Account> accounts;
}
