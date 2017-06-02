package com.codenpay;

import org.springframework.data.annotation.Id;

public class Transaction {

	@Id
	private String identifier;

	private long timestamp;
	
	private String checkoutId;
	
	private String statusId;
	private Result statusResult;
	private Card card;
	private String amount;
	private String currency;
	private String brand;
	private String type;
	private BasicPayment basicPayment;
	private String registrationId;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getCheckoutId() {
		return checkoutId;
	}
	public void setCheckoutId(String checkoutId) {
		this.checkoutId = checkoutId;
	}
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public Result getStatusResult() {
		return statusResult;
	}
	public void setStatusResult(Result statusResult) {
		this.statusResult = statusResult;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public BasicPayment getBasicPayment() {
		return basicPayment;
	}
	public void setBasicPayment(BasicPayment basicPayment) {
		this.basicPayment = basicPayment;
	}	
	public String getRegistrationId()
	{
		return registrationId;
	}
	public void setRegistrationId(String registrationId)
	{
		this.registrationId = registrationId;
	}
	
}
