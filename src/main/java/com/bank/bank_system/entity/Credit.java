package com.bank.bank_system.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private CreditType creditType;

    private Double interestRate;

    private Integer term;

    private Date creditDate;
}
