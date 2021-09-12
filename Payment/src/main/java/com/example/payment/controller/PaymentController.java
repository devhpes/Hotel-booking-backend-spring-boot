package com.example.payment.controller;

import com.example.payment.entity.TransactionDetailsEntity;
import com.example.payment.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/payment/")
@AllArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //Transaction API POST Controller which consumes and produces JSON Object. This API consumes
    // TransactionDetailsEntity body like paymentMode, id (bookingId), cardNumber, upiID and returns transaction id.
    // Which is consumed by Booking Service
    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public int addTransaction(@RequestBody TransactionDetailsEntity transactionDetailsEntity) {
        return paymentService.addTransaction(transactionDetailsEntity).getId();
    }

    // Transaction API GET controller which produces JSON object. This API consumes transactionId in URI
    @GetMapping(value = "/transaction/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetailsEntity> getTransaction(@PathVariable int transactionId) {
        return new ResponseEntity<>(paymentService.getTransaction(transactionId), HttpStatus.OK);
    }
}
