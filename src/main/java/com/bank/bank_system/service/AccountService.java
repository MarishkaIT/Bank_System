package com.bank.bank_system.service;

import com.bank.bank_system.entity.Account;
import com.bank.bank_system.entity.Client;
import com.bank.bank_system.exception.AccountNotFoundException;
import com.bank.bank_system.exception.ClientNotFoundException;
import com.bank.bank_system.exception.InsufficientBalanceException;
import com.bank.bank_system.repository.AccountRepository;
import com.bank.bank_system.repository.ClientRepository;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    private AccountRepository accountRepository;

    private ClientRepository clientRepository;

    public Account createClientAccount(Long clientId, Account account) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found!"));
        account.setClient(client);
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException("Account not found!"));
    }

    public List<Account> getAccountsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found!"));
        return accountRepository.findByClient(client);
    }

    @Transactional
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }


    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = getAccount(accountId);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(accountId, amount);
        }
        updateBalance(account, amount.negate());
    }

    private void updateBalance(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void deposit(Long accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        updateBalance(account, amount);
    }
     public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new NullPointerException("Account not found!");
        }
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in from account");
        }
        try {
            transactional(() -> {
                updateBalance(fromAccount, amount.negate());
                updateBalance(toAccount, amount);
            });
        } catch (Exception exception) {
            log.error("Error during transfer", exception);
        }
     }

     private void transactional(Runnable operation) {
        try {
            operation.run();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
     }

     private void rollbackTransaction() {
         JdbcTemplate jdbcTemplate = new JdbcTemplate();
         try {
             jdbcTemplate.execute("ROLLBACK");
             System.out.println("Transaction rolled back successfully.");
         } catch (DataAccessException e) {
             System.out.println("Error rolling back transaction: " + e.getMessage());
         }
     }
}
