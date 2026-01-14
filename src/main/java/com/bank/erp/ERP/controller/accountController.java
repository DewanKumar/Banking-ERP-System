package com.bank.erp.ERP.controller;

import com.bank.erp.ERP.entity.Account;
import com.bank.erp.ERP.entity.AccountType;
import com.bank.erp.ERP.entity.Transaction;
import com.bank.erp.ERP.repository.Accountrepository;
import com.bank.erp.ERP.service.AccountService;
import com.bank.erp.ERP.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class accountController {

    @Autowired
    private AccountService accountservice;

    @Autowired
    private TransactionService transactionservice;

    @PostMapping("/create")
    public Account createaccount(@RequestBody Account account){
        return accountservice.CreateAccount(account);
    }

    @PostMapping("/Credit")
    public String CreditAccount(@RequestParam String AccountNumber,
                                 @RequestParam double balance){
        transactionservice.Credit(AccountNumber, balance);
        return "Amount Credit successfully";
    }

    @PostMapping("/Debit")
    public String DebitAccount(@RequestParam String AccountNumber,
                               @RequestParam double balance){
        transactionservice.Debit(AccountNumber, balance);
        return "Amount Debit successfully";
    }
    @GetMapping("/Accounts")
    public List<Account> GetAllAccount(){
        return accountservice.GetAllAccounts();
    }

    @GetMapping("/id/{id}")
    public Account GetAccountById(@PathVariable Long id){
        return accountservice.getAccount(id);
    }
    @GetMapping("/number/{accountnumber}")
    public Account GetAccountByNumber(@PathVariable String accountnumber){
        return accountservice.getAccountByNumber(accountnumber);
    }
    @GetMapping("/type/{accountType}")
    public List<Account> GetAccountByType(@PathVariable String accountType){
        return accountservice.getAccountByType(accountType);
    }
    //GET /api/account/balance/search?operator=LESS&balance=1000&page=0&size=5&sort=balance,desc
    //GET /api/account/balance/search?operator=GREATER&balance=1000&page=1&size=10
    //GET /api/account/balance/search?operator=EQUAL&balance=1000
    @GetMapping("/balance/search")
    public Map<String,Object> SearchAccountsByBalance(
            @RequestParam String operator,
            @RequestParam double balance,
            Pageable pageable){
        return accountservice.getAccountByBalance(
                operator, balance, pageable
        );
    }
    //GET /api/account/balance/between?minBalance=500&maxBalance=2000&page=0&size=5&sort=balance,asc
    @GetMapping("/balance/between")
    public Map<String,Object> SearchAccountsByBalanceBetween(
            @RequestParam double min,
            @RequestParam double max,
            Pageable pageable){
        return accountservice.searchAccountsByBalanceBetween(min,max,pageable);
    }
    @DeleteMapping("/id/{id}")
    public String DeleteAccountById(@PathVariable Long id){
        accountservice.deleteAccount(id);
        return "Account Deleted Successfully";
    }

    @PutMapping("/id/{id}")
    public Account UpdateAccountById(@PathVariable Long id,@RequestBody Account account){
        return accountservice.UpdateAccount(id, account);
    }

}
