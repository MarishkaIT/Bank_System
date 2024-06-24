package com.bank.bank_system.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
