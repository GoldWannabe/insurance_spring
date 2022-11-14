package com.mju.spring.Entity;

import java.time.LocalDate;

public abstract class Insurance {
	private String insuranceID;

	public enum EInsurance {
		general, house
	};

	private EInsurance insuranceType;
	private boolean longTerm;
	private String insuranceName;
	private String specialContract;
	private String applyCondition;
	private String compensateCondition;
	private String explanation;
	private int standardFee;
	private LocalDate releaseDate;
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
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
	public int getStandardFee() {
		return standardFee;
	}
	public void setStandardFee(int standardFee) {
		this.standardFee = standardFee;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
}
