package com.codenpay;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDetails {
	private String connectorTxID1;
	private String connectorTxID2;
	private String connectorTxID3;
	private String reconciliationId;
	private String extendedDescription;
	private String acquirerResponse;

	@JsonProperty("ConnectorTxID1")
	public String getConnectorTxID1() {
		return connectorTxID1;
	}
	public void setConnectorTxID1(String connectorTxID1) {
		this.connectorTxID1 = connectorTxID1;
	}
	@JsonProperty("ConnectorTxID2")
	public String getConnectorTxID2() {
		return connectorTxID2;
	}
	public void setConnectorTxID2(String connectorTxID2) {
		this.connectorTxID2 = connectorTxID2;
	}	
	@JsonProperty("ConnectorTxID3")
	public String getConnectorTxID3() {
		return connectorTxID3;
	}
	public void setConnectorTxID3(String connectorTxID3) {
		this.connectorTxID3 = connectorTxID3;
	}	
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
