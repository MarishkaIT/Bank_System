package com.bank.bank_system.service;

import com.bank.bank_system.entity.Account;
import com.bank.bank_system.entity.Client;
import com.bank.bank_system.exception.ClientNotFoundException;
import com.bank.bank_system.repository.AccountRepository;
import com.bank.bank_system.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    private ClientRepository clientRepository;

    @Transactional
    public Account createClientAccount(Long clientId, Account account) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client not found!"));
        account.setClient(client);
        return accountRepository.save(account);
    }
}
