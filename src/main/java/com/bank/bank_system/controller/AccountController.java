package com.bank.bank_system.controller;

import com.bank.bank_system.entity.Account;
import com.bank.bank_system.entity.Transaction;
import com.bank.bank_system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(Long clientId, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.createClientAccount(clientId, account));
    }

    @PutMapping( "/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        Account existAccount = accountService.getAccount(accountId);
        if (existAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountService.updateAccount(accountId, account));
    }

    @DeleteMapping( "/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId, @RequestBody Account account) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<Transaction> deposit(@PathVariable @NotNull Long accountId, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(accountService.deposit(accountId, amount));
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<Transaction> withdraw(@PathVariable Long accountId, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(accountService.withdraw(accountId, amount));
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<Transaction> transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        return ResponseEntity.ok(accountService.transfer(fromAccountId, toAccountId, amount));
    }
}

