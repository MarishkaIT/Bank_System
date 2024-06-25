package com.bank.bank_system.controller;

import com.bank.bank_system.entity.Account;
import com.bank.bank_system.entity.Client;
import com.bank.bank_system.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;

    @GetMapping
   public List<Client> getAllClients() {
       return clientService.getAllClients();
   }

   @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");
        }
        return client;
   }

   @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return ResponseEntity.created(URI.create("/api/client/" + newClient.getId())).body(newClient);
   }

   @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client updateClient = clientService.updateClient(id, client);
        return ResponseEntity.ok(updateClient);
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
   }

   @GetMapping("/{id}/accounts")
    public List<Account> getClientAccounts(@PathVariable Long id) {
        return clientService.getClientAccount(id);
   }

   @PostMapping("/{id}/accounts")
    public ResponseEntity<Account> createClientAccount(@PathVariable Long id, @RequestBody Account account) {
        Account newAccount = clientService.createClientAccount(id, account);
        return ResponseEntity.created(URI.create("/api/clients/" + id + "/accounts/" + newAccount.getId())).body(newAccount);
   }
}
