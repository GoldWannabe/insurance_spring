package com.mju.spring.dto.contractTeam.contractManagement;

public class RenewCustomerRankDto {
	private String customerID;
	private String contractID;
	private String rankID;
	
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
		return rankID;
	}
	public void setRankID(String rankID) {
		this.rankID = rankID;
	}
	
}
