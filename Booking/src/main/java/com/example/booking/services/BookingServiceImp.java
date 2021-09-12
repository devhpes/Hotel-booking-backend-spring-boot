package com.example.booking.services;

import com.example.booking.entity.BookingInfoEntity;
import com.example.booking.exceptions.InvalidBookingIdException;
import com.example.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.booking.modal.Payment;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;


@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    //This method save the booking response (BookingInfoEntity) in the Booking Table, and also it calls getPrice and getRandomNumbers
    // methods
    @Override
    public BookingInfoEntity addBooking(BookingInfoEntity bookingInfoEntity) {
        bookingInfoEntity.setRoomNumbers(String.join(",", getRandomNumbers(bookingInfoEntity.getNumOfRooms())));
        bookingInfoEntity.setRoomPrice(getPrice(bookingInfoEntity));
        return bookingRepository.save(bookingInfoEntity);
    }

    // Price calculator method
    private int getPrice(BookingInfoEntity bookingInfoEntity) {
        return 1000 * bookingInfoEntity.getNumOfRooms();
    }

    // Random number generator method, which is used in generating random room numbers based on user input
    private ArrayList<String> getRandomNumbers(int count) {
        Random random = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(random.nextInt(upperBound)));
        }
        return numberList;
    }

    // This method add transaction for booking done by the user. This method used RestTemplate to call external
    // API which is Payment Service API. The bookingId or id is passed in the URI and with body needed to call this API
    // This method record the response of the Payment Service transaction API and save it in the Booking Table matching
    // with bookingId (This is done by findById method) and also return a message in the console if the transaction is
    // done or whose transaction id is not 0 for the respective bookingId or id.
    // This method also throws exception if the id is not found when transaction API is called with wrong bookingId or id.
    @Override
    public BookingInfoEntity addTransaction(Payment payment, int bookingId) {

        URI paymentServiceURL = null;
        try {
            paymentServiceURL = new URI("paymentServiceURL");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        BookingInfoEntity bookingInfo = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidBookingIdException(" Invalid Booking Id "));

        assert paymentServiceURL != null;
        Integer body = new RestTemplate().postForEntity("http://localhost:8083/payment/transaction",
                payment, Integer.class).getBody();

        assert body != null;
        if (body != 0) {
            bookingInfo.setTransactionId(body);
            bookingRepository.save(bookingInfo);
            String message = "Booking confirmed for user with aadhaar number: "
                    + bookingInfo.getAadharNumber() + "    |    " + "Here are the booking details:    "
                    + bookingInfo.toString();
            System.out.println(message);
            return bookingInfo;
        } else {
            return null;
        }
    }
}