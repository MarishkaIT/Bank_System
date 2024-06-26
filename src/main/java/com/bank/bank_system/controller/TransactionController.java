package com.bank.bank_system.controller;

import com.bank.bank_system.entity.Transaction;
import com.bank.bank_system.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid Transaction transaction) {
        Transaction transactions = transactionService.createTransaction(transaction.getTransactionType(), transaction.getAmount());
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionForAccount(@PathVariable @NotNull Long accountId) {
        List<Transaction> transactionList = transactionService.getTransactionForAccount(accountId);
        return ResponseEntity.ok(transactionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable @NotNull Long id) {
        Transaction transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(transaction);
    }
}
