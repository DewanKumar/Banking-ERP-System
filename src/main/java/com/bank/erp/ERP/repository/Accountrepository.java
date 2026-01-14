package com.bank.erp.ERP.repository;

import com.bank.erp.ERP.entity.Account;
import com.bank.erp.ERP.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface Accountrepository extends JpaRepository<Account,Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByAccountType(AccountType AccountType);

    Page<Account> findByBalanceGreaterThan(double balance, Pageable pageable );

    Page<Account> findByBalanceLessThan(double balance, Pageable pageable);
    Page<Account> findByBalance(double balance, Pageable pageable);

    Page<Account>  findByBalanceBetween(double min, double max, Pageable pageable);
}
