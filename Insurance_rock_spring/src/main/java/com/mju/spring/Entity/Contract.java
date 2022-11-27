package com.mju.spring.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Contract {

	private String customerID;
	private String customerName;
	private String phoneNum;
	private String insuranceID;
	private String insuranceName;
	private String contractID;
	private int insuranceFee;
	private int paymentCycle;
	private int provisionFee;
	private int securityFee;
	private int period;
	private LocalDate startDate;
	private LocalDate endDate;
	private int unpaidFee;

	private ArrayList<String> accidentHistory = new ArrayList<String>();
	private int num;

	// 심사 탈락 이유
	private String reason;
	
	public Contract () {
		this.contractID = UUID.randomUUID().toString();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getNum() {
		return num;
	}

	public ArrayList<String> getAccidentHistory() {
		return accidentHistory;
	}

	public void setAccidentHistory(ArrayList<String> accidentHistory) {
		this.accidentHistory = accidentHistory;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public String getContractID() {
		return contractID;
	}

//	public void setContractID() {
//		this.contractID = UUID.randomUUID().toString();
//	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
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

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getProvisionFee() {
		return provisionFee;
	}

	public void setProvisionFee(int provisionFee) {
		this.provisionFee = provisionFee;
	}

	public int getSecurityFee() {
		return securityFee;
	}

	public void setSecurityFee(int securityFee) {
		this.securityFee = securityFee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getUnpaidFee() {
		return unpaidFee;
	}

	public void setUnpaidFee(int unpaidFee) {
		this.unpaidFee = unpaidFee;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

}