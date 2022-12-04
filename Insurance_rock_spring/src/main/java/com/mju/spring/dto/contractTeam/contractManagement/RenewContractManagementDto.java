package com.mju.spring.dto.contractTeam.contractManagement;

public class RenewContractManagementDto {
	private String contractID;
	private String customerID;
	private String insuranceID;
	private int paymentCycle;
	private int insuranceFee;
	private int securityFee;
	private int period;
	
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	public int getPaymentCycle() {
		return paymentCycle;
	}
	public void setPaymentCycle(int paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	public int getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public int getSecurityFee() {
		return securityFee;
	}
	public void setSecurityFee(int securityFee) {
		this.securityFee = securityFee;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	
}
