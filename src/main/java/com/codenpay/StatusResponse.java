package com.codenpay;

import java.util.Map;

public class StatusResponse extends CheckoutResponse{
	private String registrationId;
	private String paymentType;
	private String paymentBrand;
	private String amount;
	private String currency;
	private Result result;
	private ResultDetails resultDetails;
	private Card card;

	private Map<String, String> customParameters;

	public String getRegistrationId()
	{
		return registrationId;
	}
	public void setRegistrationId(String registrationId)
	{
		this.registrationId = registrationId;
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
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public ResultDetails getResultDetails() {
		return resultDetails;
	}
	public void setResultDetails(ResultDetails resultDetails) {
		this.resultDetails = resultDetails;
	}	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Map<String, String> getCustomParameters() {
		return customParameters;
	}
	public void setCustomParameters(Map<String, String> customParameters)
	{
		this.customParameters = customParameters;
	}
	
}
