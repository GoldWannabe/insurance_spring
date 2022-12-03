package com.mju.spring.dto;

import com.mju.spring.entity.Insurance.EInsurance;

public class InsuranceTypeAndTermDto {
	public enum EInsurance {
		general, house
	};

	private com.mju.spring.entity.Insurance.EInsurance insuranceType;
	private boolean longTerm;
	
	public com.mju.spring.entity.Insurance.EInsurance getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(com.mju.spring.entity.Insurance.EInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
}
