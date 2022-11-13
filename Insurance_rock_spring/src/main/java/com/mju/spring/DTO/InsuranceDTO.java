package com.mju.spring.DTO;


public class InsuranceDTO {
	public enum EInsurance {
		general, house
	};

	private EInsurance insuranceType;
	private boolean longTerm;
	
	public EInsurance getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(EInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
}
