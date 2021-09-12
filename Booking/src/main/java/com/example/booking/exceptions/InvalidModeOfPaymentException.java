package com.example.booking.exceptions;

public class InvalidModeOfPaymentException extends RuntimeException{

    public InvalidModeOfPaymentException(String message){
        super(message);
    }
}
