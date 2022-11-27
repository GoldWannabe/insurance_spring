package com.mju.spring.dto.damageAssessment.compansate;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContractProvisionDto {
	//계약 id, 보험id, 보험이름, 담보액, 지급액, 시작일, 만기일
	private String contractID;
	private String insuranceID;
	private String insuranceName;
	private int securityFee;
	private int provisionFee;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
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
	public int getSecurityFee() {
		return securityFee;
	}
	public void setSecurityFee(int securityFee) {
		this.securityFee = securityFee;
	}
	public int getProvisionFee() {
		return provisionFee;
	}
	public void setProvisionFee(int provisionFee) {
		this.provisionFee = provisionFee;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}
