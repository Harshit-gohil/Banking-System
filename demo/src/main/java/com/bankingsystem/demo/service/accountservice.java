package com.bankingsystem.demo.service;

import com.bankingsystem.demo.model.account;
import com.bankingsystem.demo.model.transaction_type;
import com.bankingsystem.demo.model.transactions;
import com.bankingsystem.demo.repository.accountrepository;
import com.bankingsystem.demo.repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class accountservice {

    @Autowired
    private accountrepository accRepo;
    @Autowired
    private transactionRepository transactionRepository;

    private Long GenrateAccNo(){
        return Long.valueOf(1000000000L+ new Random().nextLong(900000000));
    }

    public account OpenAccount(String name,Long balance){

        account acc=new account();
        acc.setAccountNo(GenrateAccNo());
        acc.setName(name);
        acc.setBalance(balance);
        acc.setDate(LocalDateTime.now());
        accRepo.save(acc);

        return acc;
    }

    public account GetAccount(long AccNo){
        account acc=accRepo.findById(AccNo).orElseThrow(()-> new RuntimeException("account not found"));
        return acc;
    }

    public account withdraw(long AccNo,long amount){

        account Acc= accRepo.findById(AccNo).orElseThrow(()-> new RuntimeException("account not found"));

        if (Acc.getBalance()<amount){
            throw new RuntimeException("Insufficient Balance");
        }
        Acc.setBalance(Acc.getBalance()-amount);
        accRepo.save(Acc);

        SaveStatement(AccNo,transaction_type.withdraw,amount,LocalDateTime.now());

        return Acc;
    }

    public account deposit(long AccNo,Long amount){

        account Acc= accRepo.findById(AccNo).orElseThrow(()-> new RuntimeException("account not found"));

        if(amount==null || amount <0){
            throw new RuntimeException("invalid amount");
        }
        Acc.setBalance(Acc.getBalance()+amount);
        accRepo.save(Acc);

        SaveStatement(AccNo,transaction_type.deposit,amount,LocalDateTime.now());

        return Acc;
    }

    public String transfer(long ToAcc,long FromAcc,long amount){

        account To=accRepo.findById(ToAcc).orElseThrow(()-> new RuntimeException("reciver account not found"));
        account From=accRepo.findById(FromAcc).orElseThrow(()-> new RuntimeException("Sender account not found"));

        if(From.getBalance()<amount){
            throw new RuntimeException("Insufficient Balance in ");

        }
        From.setBalance(From.getBalance()-amount);
        To.setBalance(To.getBalance()+amount);

        SaveStatement(To.getAccountNo(),transaction_type.deposit,amount,LocalDateTime.now());
        SaveStatement(From.getAccountNo(),transaction_type.withdraw,amount,LocalDateTime.now());

        accRepo.save(To);
        accRepo.save(From);

        String s=("Done! ₹"+amount+" sent successfully to "+To.getName()+"🎉");
        return s;

    }

    public void SaveStatement(long AccNo, transaction_type type,long amount,LocalDateTime dateTime){
        transactions transaction=new transactions();
        transaction.setAccNo(AccNo);
        transaction.setType(type);
        transaction.setDateTime(dateTime);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);

    }

    public void deleteAccount(long Acc_No){
        accRepo.delete(accRepo.getReferenceById(Acc_No));
    }


    public List<transactions> Statement(long AccNo) {
        List<transactions> transaction =transactionRepository.findByAccNo(AccNo);

        if(transaction.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }

        return transaction;
    }

    public String UpdateName(Long Acc_No,String name){
        account account=accRepo.findById(Acc_No).orElseThrow(()-> new RuntimeException("reciver account not found"));

        String Pname=account.getName();
        account.setName(name);
        String s=("Your bank account name has been updated successfully.\n"
        +"Old Name :"+Pname +"\n"
        +"New Name :"+name);

        return s;
    }
}
