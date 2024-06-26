package com.bank.bank_system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Instant transactionDate;




    public Transaction(Long fromAccountId, Long toAccountId, BigDecimal amount) {

    }

    public Transaction(Account account, BigDecimal amount, TransactionType transactionType) {
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
    }
}
