package com.example.payment.exception;

public class InvalidModeOfPaymentException extends RuntimeException{

    public InvalidModeOfPaymentException(String message){
        super(message);
    }
}
