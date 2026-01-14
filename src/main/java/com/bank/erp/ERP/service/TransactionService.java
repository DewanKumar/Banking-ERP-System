package com.bank.erp.ERP.service;

import com.bank.erp.ERP.entity.Account;
import com.bank.erp.ERP.entity.Transaction;
import com.bank.erp.ERP.entity.TransactionType;
import com.bank.erp.ERP.repository.TransactionRepository;
import com.bank.erp.ERP.repository.Accountrepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionrepository;

    @Autowired
    private Accountrepository Accountrepository;
    @Transactional
    public void Credit(String accountNumber,Double amount){
        Account account = Accountrepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new RuntimeException("Account not found"));
        account.setBalance(account.getBalance()+amount);
        Accountrepository.save(account);

        Transaction tx=new Transaction();
        tx.setAccount(account);
        tx.setTransactionType(TransactionType.Credit);
        tx.setAmount(amount);
        transactionrepository.save(tx);
    }

    @Transactional
    public void Debit(String accountNumber,Double amount){
        Account account=Accountrepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new RuntimeException("Account not found"));

        if (amount <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debit amount must be greater than zero"
            );
        }

        if (amount > account.getBalance()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insufficient balance"
            );
        }
        account.setBalance(account.getBalance()-amount);
        Accountrepository.save(account);
        Transaction tx=new Transaction();
        tx.setAccount(account);
        tx.setTransactionType(TransactionType.Debit);
        tx.setAmount(amount);
        transactionrepository.save(tx);
    }
}
