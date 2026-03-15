package com.bankingsystem.demo.repository;

import com.bankingsystem.demo.model.transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface transactionRepository extends JpaRepository<transactions,Long> {
    List<transactions> findByAccNo(long AccNo);
}
