package com.example.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleInvalidBookingIdException(InvalidBookingIdException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage("Invalid Booking Id");
        errorObject.setStatusCode(400);
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleInvalidModeOfPaymentException(InvalidModeOfPaymentException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage("Invalid mode of payment");
        errorObject.setStatusCode(400);
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
