package com.example.booking.services;

import com.example.booking.entity.BookingInfoEntity;
import com.example.booking.modal.Payment;

public interface BookingService {

    public BookingInfoEntity addBooking(BookingInfoEntity booking);

    public BookingInfoEntity addTransaction(Payment payment, int bookingId);


}
