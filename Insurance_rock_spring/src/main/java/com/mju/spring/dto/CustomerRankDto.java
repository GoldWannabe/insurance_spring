package com.mju.spring.dto;

public class CustomerRankDto {
	
	private String customerID;
	private String contractID;
	private String RankID;
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getRankID() {
		return RankID;
	}
	public void setRankID(String rankID) {
		RankID = rankID;
	}

}
