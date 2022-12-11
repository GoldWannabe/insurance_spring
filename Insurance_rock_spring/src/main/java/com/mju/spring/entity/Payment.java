package com.mju.spring.entity;

import java.time.LocalDate;

import com.mju.spring.entity.Insurance.EInsurance;

public class Payment {
	private String paymentID;
	private String customerID;
	private String customerName;
	private String customerPhoneNum;
	private String accountNum;
	private String cardOrBankName;
	private int insuranceFee;
	private String insuranceName;
	private LocalDate paidDate;
	private String contractID;
	private EInsurance insuranceType;
	
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhoneNum() {
		return customerPhoneNum;
	}
	public void setCustomerPhoneNum(String customerPhoneNum) {
		this.customerPhoneNum = customerPhoneNum;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getCardOrBankName() {
		return cardOrBankName;
	}
	public void setCardOrBankName(String cardOrBankName) {
		this.cardOrBankName = cardOrBankName;
	}
	public int getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public LocalDate getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public EInsurance getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = EInsurance.valueOf(insuranceType);
	}
	
}
