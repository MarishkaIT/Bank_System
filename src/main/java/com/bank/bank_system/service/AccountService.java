package com.bank.bank_system.service;

import com.bank.bank_system.entity.Account;
import com.bank.bank_system.entity.Client;
import com.bank.bank_system.exception.AccountNotFoundException;
import com.bank.bank_system.exception.ClientNotFoundException;
import com.bank.bank_system.exception.InsufficientBalanceException;
import com.bank.bank_system.repository.AccountRepository;
import com.bank.bank_system.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    private ClientRepository clientRepository;

    @Transactional
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

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException("Account not found!"));
    }

    public void withdraw(Long accountId, Double amount) {
        Account account = getAccount(accountId);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(accountId, amount);
        }
        account.setBalance(account.getBalance());
        accountRepository.save(account);
    }

    public void deposit(Long accountId, Double amount) {
        Account account = getAccount(accountId);
        account.setBalance(account.getBalance());
        accountRepository.save(account);
    }
     public void transfer(Long fromAccountId, Long toAccountId, Double amount) {
        withdraw(fromAccountId, amount);
        deposit(toAccountId, amount);
     }

     @Transactional
     public void updateRecipientAccount(long recipientAccountId, BigDecimal amount) {
        Account recipientAccount = accountRepository.findById(recipientAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Recipient account not found!"));

        recipientAccount.setBalance(recipientAccount.getBalance());
        accountRepository.save(recipientAccount);

     }
}
