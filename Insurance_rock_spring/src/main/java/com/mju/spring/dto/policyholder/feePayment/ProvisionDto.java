package com.mju.spring.dto.policyholder.feePayment;

import java.time.LocalDate;

import com.mju.spring.entity.Insurance.EInsurance;

public class ProvisionDto {
//정보(보험이름,보험종류,장기여부,은행명,계좌번호,보상금액,보상일)를 
	private String insuranceName;
	private EInsurance insuranceType;
	private boolean longTerm;
	private String bankName;
	private String accountNum;
	private int compensation;
	private LocalDate compensationDate;
	
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public EInsurance getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = EInsurance.valueOf(insuranceType);
	}
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
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
	
	
}
