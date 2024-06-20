package com.bank.bank_system.repository;

import com.bank.bank_system.entity.Credit;
import com.bank.bank_system.entity.CreditType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByType(CreditType type);
}
