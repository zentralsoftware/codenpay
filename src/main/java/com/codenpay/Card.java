package com.codenpay;

public class Card {

	private String bin;
	private String last4Digits;
	private String holder;
	private String expiryMonth;
	private String expiryYear;
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public String getLast4Digits() {
		return last4Digits;
	}
	public void setLast4Digits(String last4Digits) {
		this.last4Digits = last4Digits;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}	
	
}
