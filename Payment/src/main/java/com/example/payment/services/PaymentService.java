package com.example.payment.services;

import com.example.payment.entity.TransactionDetailsEntity;

public interface PaymentService {

    public TransactionDetailsEntity addTransaction(TransactionDetailsEntity transactionDetailsEntity);

    public TransactionDetailsEntity getTransaction(int id);
}

