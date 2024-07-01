package com.bank.bank_system.service;

import com.bank.bank_system.entity.Account;
import com.bank.bank_system.entity.Client;
import com.bank.bank_system.exception.ClientNotFoundException;
import com.bank.bank_system.repository.AccountRepository;
import com.bank.bank_system.repository.ClientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientService implements UserDetailsService {
    ClientRepository clientRepository;

    AccountRepository accountRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Client existClient = getClientById(id);
        existClient.setFirstName(client.getFirstName());
        existClient.setLastName(client.getLastName());
        existClient.setMiddleName(client.getMiddleName());
        existClient.setEmail(client.getEmail());
        existClient.setPhoneNumber(client.getPhoneNumber());
        existClient.setAddress(client.getAddress());
        return clientRepository.save(existClient);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Account> getClientAccount(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    public Account createClientAccount(Long clientId, Account account) {
        Client client = getClientById(clientId);
        account.setClient(client);
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws ClientNotFoundException {
        return clientRepository.findByEmail(email)
                .map(client -> new User(
                        client.getEmail(),
                        client.getPassword(),
                        Collections.singleton(client.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve client: " + email));
    }
}
