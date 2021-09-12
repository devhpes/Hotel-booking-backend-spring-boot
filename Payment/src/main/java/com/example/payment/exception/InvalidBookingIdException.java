package com.example.payment.exception;

public class InvalidBookingIdException extends RuntimeException{

    public InvalidBookingIdException(String message){
        super(message);

    }
}
