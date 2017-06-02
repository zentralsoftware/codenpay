package com.codenpay;

import java.util.List;

public class CheckoutRequest {

	private Authentication authentication;
	private BasicPayment basicPayment;
	private List<String> registrationIds;

	public Authentication getAuthentication() {
		return authentication;
	}
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	public BasicPayment getBasicPayment() {
		return basicPayment;
	}
	public void setBasicPayment(BasicPayment basicPayment) {
		this.basicPayment = basicPayment;
	}
	public List<String> getRegistrationIds() {
		return registrationIds;
	}
	public void setRegistrationIds(List<String> registrationIds) {
		this.registrationIds = registrationIds;
	}
	
}
