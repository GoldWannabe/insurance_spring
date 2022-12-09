package com.mju.spring.dto.policyholder.feePayment;

public class PaymentDto {
	//보험 이름, 분납/일시불 여부, 보험료, 납부 한 금액,미납액, 보험ID, 고객 ID, 계약ID	
	
	private String insuranceID;
	private String insuranceName;
	private boolean fullPayment;
	private String customerID;
	private int insuranceFee;
	private int unpaidFee;
	private String contractID;
	
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public boolean isFullPayment() {
		return fullPayment;
	}
	public void setFullPayment(boolean fullPayment) {
		this.fullPayment = fullPayment;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public int getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public int getUnpaidFee() {
		return unpaidFee;
	}
	public void setUnpaidFee(int unpaidFee) {
		this.unpaidFee = unpaidFee;
	}
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	
	
}
