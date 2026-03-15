package com.bankingsystem.demo.controller;


import com.bankingsystem.demo.model.account;
import com.bankingsystem.demo.model.transactions;
import com.bankingsystem.demo.service.accountservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class accountcontroller {

    @Autowired
    private accountservice accservice;


    @PostMapping("/open")
    public account open(@RequestParam String name,@RequestParam Long balance){
        return accservice.OpenAccount(name,balance);
    }

    @GetMapping("/GetAccount")
    public account GetAccount(@RequestParam long AccNo){
        return accservice.GetAccount(AccNo);
    }
    @PostMapping("/withdraw")
    public account withdraw(@RequestParam long AccNo,@RequestParam long amount){
        return accservice.withdraw(AccNo,amount);
    }

    @PostMapping("/deposit")
    public account deposit(@RequestParam long AccNo,@RequestParam long amount){
        return accservice.deposit(AccNo,amount);
    }

    @PostMapping("/Transfer")
    public String Transfer(@RequestParam long to,@RequestParam long from , long amount){
       return accservice.transfer(to,from,amount);
    }

    @DeleteMapping("/DeleteAccount")
    public void deletAccount(@RequestParam long Acc_no){
        accservice.deleteAccount(Acc_no);
    }

    @GetMapping("/Statement")
    public List<transactions> Statement(@RequestParam long Acc_No){
        return accservice.Statement(Acc_No);
    }

    @PostMapping("/UpdateName")
    public String UpdateName(@RequestParam long Acc_No,@RequestParam String Name){
        return accservice.UpdateName(Acc_No,Name);
    }
}
