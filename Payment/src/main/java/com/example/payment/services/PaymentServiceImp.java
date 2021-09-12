package com.example.payment.services;

import com.example.payment.entity.TransactionDetailsEntity;
import com.example.payment.exception.InvalidBookingIdException;
import com.example.payment.exception.InvalidModeOfPaymentException;
import com.example.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // This method add transaction details when payment is made using specified body and details are
    // saved in the transaction table. This API is called by Booking Service.
    public TransactionDetailsEntity addTransaction(TransactionDetailsEntity transactionDetailsEntity) {
        if(transactionDetailsEntity.getPaymentMode().equals("CARD") || transactionDetailsEntity.getPaymentMode()
                .equals("UPI"))
        return paymentRepository.save(transactionDetailsEntity);
        else {
            throw new InvalidModeOfPaymentException(" Invalid mode of payment ");
        }
    }

    // This method return transaction. Booking id is passed in this method, which will return the
    // payment details
    public TransactionDetailsEntity getTransaction(int id) {
        return this.paymentRepository.findById(id).orElseThrow(() -> new InvalidBookingIdException(" Invalid Booking Id "));
    }

}
