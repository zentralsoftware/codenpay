package com.codenpay;

public class Result {

	private String code;
	private String description;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("code: " + code);
		sb.append(",");
		sb.append("description: " + description);
		return sb.toString();
	}
}
