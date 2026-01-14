package com.bank.erp.ERP.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity

public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String fullname;
    private String email;
    private String phone;
    private String address;

    private LocalDateTime createdAt=LocalDateTime.now();
}
