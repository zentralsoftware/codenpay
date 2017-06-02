package com.codenpay;

import java.io.IOException;

public interface CopyAndPay {

	public CheckoutResponse checkout(CheckoutRequest request) throws IOException;
	public StatusResponse checkStatus(String checkoutId) throws IOException;	
	public PaymentResponse payments(PaymentRequest request) throws IOException;
	public CheckoutResponse oneclick(CheckoutRequest request) throws IOException;
}
