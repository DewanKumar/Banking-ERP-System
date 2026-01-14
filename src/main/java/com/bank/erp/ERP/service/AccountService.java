package com.bank.erp.ERP.service;

import com.bank.erp.ERP.entity.Account;
import com.bank.erp.ERP.entity.AccountType;
import com.bank.erp.ERP.exception.ResourceNotFoundException;
import com.bank.erp.ERP.repository.Accountrepository;
import com.bank.erp.ERP.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private Accountrepository Accountrepository;

    @Autowired
    private TransactionRepository Transactionrepository;

    public Account CreateAccount(Account account){
        if (account.getBalance() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Balance cannot be Less then 0"
            );
        }
        return Accountrepository.save(account);
    }

    public List<Account> GetAllAccounts(){
        return Accountrepository.findAll();
    }
    public Account getAccount(Long id){
        return Accountrepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        "Account not found with id: " + id
                ));
    }
    public Account getAccountByNumber(String AccountNumber){
        return Accountrepository.findByAccountNumber(AccountNumber).orElseThrow(()->
                new ResourceNotFoundException(
                        "Account not found with AccountNumber: " + AccountNumber
                ));
    }
    public List<Account> getAccountByType(String type){
        AccountType accountType;

        try{
            accountType = AccountType.valueOf(type.toUpperCase());
        }catch(IllegalArgumentException e){
            throw new ResourceNotFoundException(
                    "Account not found with AccountType: " + type
            );
        }
        return Accountrepository.findByAccountType(accountType);
    }
    public Map<String,Object> getAccountByBalance(String operator, double balance, Pageable pageable){
        Page<Account> pag;

        switch (operator.toUpperCase()){
            case "LESS":
                pag = Accountrepository.findByBalanceLessThan(balance,pageable);
                break;
            case "GREATER":
                pag = Accountrepository.findByBalanceGreaterThan(balance,pageable);
                break;
            case "EQUAL":
                pag = Accountrepository.findByBalance(balance,pageable);
                break;
            default:
                throw new ResourceNotFoundException(
                        "invalid operator (use LESS, GREATER, EQUAL)"
                );
        }
        Map<String, Object> response = new HashMap<>();
        response.put("operator", operator.toUpperCase());
        response.put("count", pag.getTotalElements());
        response.put("totalPages", pag.getTotalPages());
        response.put("currentPage", pag.getNumber());
        response.put("accounts", pag.getContent());
        return response;
    }
    public Map<String,Object> searchAccountsByBalanceBetween(
            double minBalance,
            double maxBalance,
            Pageable pageable)
    {

        if(minBalance > maxBalance){
            throw new ResourceNotFoundException(
                    "minBalance cannot be greater than maxBalance"
            );
        }
        Page<Account>page=Accountrepository.findByBalanceBetween(minBalance, maxBalance,pageable);
        Map<String,Object> map = new HashMap<>();
        map.put("operator","BETWEEN");
        map.put("minBalance",minBalance);
        map.put("maxBalance",maxBalance);
        map.put("count",page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        map.put("currentPage",page.getNumber());
        map.put("accounts",page.getContent());
        return map;

    }
    public Account UpdateAccount(Long id,Account Updateaccount){
        Account account=Accountrepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "Account not found with AccountNumber: " + Updateaccount
                )
        );
        if (Updateaccount.getBalance() < 0) {
            throw new ResourceNotFoundException(
                    "Balance cannot be negative"
            );
        }
        account.setBalance(Updateaccount.getBalance());
        account.setAccountType(Updateaccount.getAccountType());

        return Accountrepository.save(account);
    }
    @Transactional
    public void deleteAccount(Long id) {

        Account account = Accountrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account not found with id: " + id
                ));
        Transactionrepository.deleteByAccount_Id(id);
        Accountrepository.delete(account);
    }

}
