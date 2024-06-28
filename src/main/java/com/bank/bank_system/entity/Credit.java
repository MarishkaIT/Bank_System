package com.bank.bank_system.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_type")
    private CreditType creditType;

    private Double interestRate;

    private Integer term;

    @Temporal(TemporalType.DATE)
    private Date creditDate;
}
