package com.codenpay;

public class BasicPayment {

	private String amount;
	private String currency;
	private String paymentType;
	private String paymentBrand;

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentBrand() {
		return paymentBrand;
	}
	public void setPaymentBrand(String paymentBrand) {
		this.paymentBrand = paymentBrand;
	}	
		
}
