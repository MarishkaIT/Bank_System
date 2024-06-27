package com.bank.bank_system.service;

import com.bank.bank_system.entity.Transaction;
import com.bank.bank_system.entity.TransactionType;
import com.bank.bank_system.exception.NotFoundTransactionException;
import com.bank.bank_system.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionService {

    TransactionRepository transactionRepository;

    AccountService accountService;


    public Transaction createTransaction(TransactionType type, BigDecimal amount) {
        Transaction transaction = Transaction.builder()
                .transactionType(type)
                .amount(amount)
                .transactionDate(Instant.now())
                .build();
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionForAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new NotFoundTransactionException("Transaction not found!"));
    }
}
