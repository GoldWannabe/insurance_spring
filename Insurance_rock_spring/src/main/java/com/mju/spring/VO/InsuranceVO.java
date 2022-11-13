package com.mju.spring.VO;


public class InsuranceVO {
	public enum EInsurance {
		general, house
	};

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
	private EInsurance insuranceType;
	private boolean longTerm;
}
