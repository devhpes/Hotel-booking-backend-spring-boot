package com.example.booking.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

	private String paymentMode;
	
	private int bookingId;
	
	private String upiId;
	
	private String cardNumber;
}
