package com.mju.spring.entity;

import java.time.LocalDate;

import com.mju.spring.entity.Insurance.EInsurance;


public class Provision {
	//
	private String provisionID;
	private String customerID;
	private String customerName;
	private String customerPhoneNum;
	private String accountNum;
	private String bankName;
	private int compensation;
	private LocalDate compensationDate;
	private String insuranceName;
	private boolean longTerm;
	private String contractID;
	private Insurance.EInsurance insuranceType;
	
	public String getProvisionID() {
		return provisionID;
	}
	public void setProvisionID(String provisionID) {
		this.provisionID = provisionID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getCompensation() {
		return compensation;
	}
	public void setCompensation(int compensation) {
		this.compensation = compensation;
	}
	public LocalDate getCompensationDate() {
		return compensationDate;
	}
	public void setCompensationDate(LocalDate compensationDate) {
		this.compensationDate = compensationDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
	public EInsurance getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(EInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}
	

}
