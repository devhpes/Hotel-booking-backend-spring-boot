package com.example.booking.controller;

import com.example.booking.entity.BookingInfoEntity;
import com.example.booking.exceptions.InvalidModeOfPaymentException;
import com.example.booking.modal.Payment;
import com.example.booking.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class BookingController {

    private BookingService bookingService;

    // Booking API Controller. This API consumes JSON object with body fromDate, toDate, aadharNumber
    // and numOfRooms and produces the JSON response which is also saved in the Booking Table
    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> addBooking(@RequestBody BookingInfoEntity bookingInfoEntity) {
        return new ResponseEntity<BookingInfoEntity>(bookingService.addBooking(bookingInfoEntity), HttpStatus.CREATED);
    }

    //Booking Payment API Controller. This API calls Payment transaction API for payment of the respective bookingId or
    // id. This method also throws exception if paymentMode is not CARD or UPI.
    @PostMapping(value = "/booking/{bookingId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> addTransaction(@RequestBody Payment payment, @PathVariable int bookingId) {

        if (payment.getPaymentMode().equals("CARD") || (payment.getPaymentMode().equals("UPI"))) {
            return new ResponseEntity<>(bookingService.addTransaction(payment, bookingId), HttpStatus.CREATED);
        } else {
            throw new InvalidModeOfPaymentException(" Invalid mode of payment ");
        }
    }
}

