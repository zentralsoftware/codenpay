package com.codenpay;

public class ResultDetails {

	private String reconciliationId;
	private String extendedDescription;
	private String acquirerResponse;

	public String getReconciliationId() {
		return reconciliationId;
	}
	public void setReconciliationId(String reconciliationId) {
		this.reconciliationId = reconciliationId;
	}

	@JsonProperty("ExtendedDescription")
	public String getExtendedDescription() {
		return extendedDescription;
	}
	public void setExtendedDescription(String extendedDescription) {
		this.extendedDescription = extendedDescription;
	}

	@JsonProperty("AcquirerResponse")	
	public String getAcquirerResponse() {
		return acquirerResponse;
	}
	public void setAcquirerResponse(String acquirerResponse) {
		this.acquirerResponse = acquirerResponse;
	}	
}
