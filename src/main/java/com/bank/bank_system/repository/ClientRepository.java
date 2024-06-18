package com.bank.bank_system.repository;

import com.bank.bank_system.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
