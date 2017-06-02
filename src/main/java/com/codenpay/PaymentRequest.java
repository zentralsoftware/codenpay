package com.codenpay;

public class PaymentRequest {

	private Authentication authentication;
	private BasicPayment basicPayment;
	private boolean createRegistration = true;

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
	public boolean isCreateRegistration()
	{
		return createRegistration;
	}
	public void setCreateRegistration(boolean createRegistration)
	{
		this.createRegistration = createRegistration;
	}
	
}
