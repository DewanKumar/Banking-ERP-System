package com.bank.erp.ERP.repository;

import com.bank.erp.ERP.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByAccount_Id(Long accountId);
    void deleteByAccount_Id(Long accountId);

}
