package com.bankingsystem.demo.repository;

import com.bankingsystem.demo.model.account;
import com.bankingsystem.demo.service.accountservice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface accountrepository extends JpaRepository<account,Long> {

}
