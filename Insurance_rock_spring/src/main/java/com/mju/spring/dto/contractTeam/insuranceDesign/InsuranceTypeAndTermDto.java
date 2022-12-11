package com.mju.spring.dto.contractTeam.insuranceDesign;

import com.mju.spring.entity.Insurance.EInsurance;

public class InsuranceTypeAndTermDto {

	private EInsurance insuranceType;
	private boolean longTerm;
	private double[] premiumRate;
	
	public double[] getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(double[] premiumRate) {
		this.premiumRate = premiumRate;
	}
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
