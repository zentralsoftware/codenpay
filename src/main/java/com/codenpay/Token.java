package com.codenpay;

import org.springframework.data.annotation.Id;

public class Token {

	@Id
	private String identifier;
	
	private String registrationId;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
