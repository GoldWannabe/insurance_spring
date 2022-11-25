package com.mju.spring.dto.financialDirector.insuranceJudge;

import com.mju.spring.entity.Insurance.EInsurance;

public class RegisterInsuranceDto {
	private String insuranceName;
	private EInsurance insuranceType;
	private int standardFee;
	
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public EInsurance getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(EInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}
	public int getStandardFee() {
		return standardFee;
	}
	public void setStandardFee(int standardFee) {
		this.standardFee = standardFee;
	}
	
	
}
