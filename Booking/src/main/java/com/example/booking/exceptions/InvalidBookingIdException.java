package com.example.booking.exceptions;

public class InvalidBookingIdException extends RuntimeException{

    public InvalidBookingIdException(String message){
        super(message);

    }
}
