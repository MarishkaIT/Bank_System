package com.bank.bank_system.repository;

import com.bank.bank_system.entity.Account;
import com.bank.bank_system.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);

    List<Account> findByClient(Client client);
}
