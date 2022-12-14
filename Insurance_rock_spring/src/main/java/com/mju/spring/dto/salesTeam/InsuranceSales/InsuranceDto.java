package com.mju.spring.dto.salesTeam.InsuranceSales;


public class InsuranceDto {

	private String insuranceType;
	private boolean longTerm;
	private double[] premiumRate;
	private String insuranceName;
	private String specialContract;
	private String applyCondition;
	private String compensateCondition;
	private String explanation;
	private int standardFee;
	
	public int getStandardFee() {
		return standardFee;
	}
	public void setStandardFee(int standardFee) {
		this.standardFee = standardFee;
	}
	public double[] getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(double[] premiumRate) {
		this.premiumRate = premiumRate;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getSpecialContract() {
		return specialContract;
	}
	public void setSpecialContract(String specialContract) {
		this.specialContract = specialContract;
	}
	public String getApplyCondition() {
		return applyCondition;
	}
	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}
	public String getCompensateCondition() {
		return compensateCondition;
	}
	public void setCompensateCondition(String compensateCondition) {
		this.compensateCondition = compensateCondition;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
	
}
