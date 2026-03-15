package com.bankingsystem.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class account {
    @Id
    private Long AccountNo;
    private String name;
    private Long balance;
    private LocalDateTime date;

    public Long getAccountNo() {
        return AccountNo;
    }

    public Long getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setAccountNo(Long accountNo) {
        AccountNo = accountNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
