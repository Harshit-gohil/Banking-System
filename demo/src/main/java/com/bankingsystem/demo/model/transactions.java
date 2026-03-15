package com.bankingsystem.demo.model;


import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long accNo;
    @Enumerated(EnumType.STRING)
    private transaction_type type;
    private long amount;
    private LocalDateTime dateTime;

    public long getAccNo() {
        return accNo;
    }

    public transaction_type getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setAccNo(long AccNo) {
        this.accNo = AccNo;
    }

    public void setType(transaction_type type) {
        this.type = type;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
