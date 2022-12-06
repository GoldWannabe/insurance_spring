package com.mju.spring.dto.contractTeam.contractManagement;

import java.time.LocalDate;


public class InsuranceDetailsDto {
	//계약번호,보험이름, 가입자명, 연락처, 보험가입일,보험 만료일,담보액,보험료,납부방식, 미납액,등급, 사고이력
			//등급은 따로 테이블 만들어서 출력???(material, fireFacilities, height, scale,  surroundingFacilities, purpose)RankID로 불러오기.
			//사고이력도 테이블 따로 만들어서 출력(accidentID, contractID),accidentID로 accident관련 정보 다 불러오기.
	private String contractID;
	private String insuranceName;
	private String customerName;
	private String customerPhoneNum;
	private LocalDate startDate;
	private LocalDate endDate;
	private int securityFee;
	private int insuranceFee;
	private int paymentCycle;
	private int unpaidFee;
	//등급
	private String material;
	private double fireFacilities;
	private boolean height;
	private int scale;
	private double surroundingFacilities;
	private String purpose;
	//사고이력
	private String accidentID;
	private LocalDate accidentDate;
	private String content;
	private int totalCost;
	private int damagePer;
	private String kindOfCost;
	private boolean payCompleted;
	private int liablityRate;
	private int liablityCost;
	
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhoneNum() {
		return customerPhoneNum;
	}
	public void setCustomerPhoneNum(String customerPhoneNum) {
		this.customerPhoneNum = customerPhoneNum;
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
	public int getSecurityFee() {
		return securityFee;
	}
	public void setSecurityFee(int securityFee) {
		this.securityFee = securityFee;
	}
	public int getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public int getPaymentCycle() {
		return paymentCycle;
	}
	public void setPaymentCycle(int paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	public int getUnpaidFee() {
		return unpaidFee;
	}
	public void setUnpaidFee(int unpaidFee) {
		this.unpaidFee = unpaidFee;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public double getFireFacilities() {
		return fireFacilities;
	}
	public void setFireFacilities(double fireFacilities) {
		this.fireFacilities = fireFacilities;
	}
	public boolean isHeight() {
		return height;
	}
	public void setHeight(boolean height) {
		this.height = height;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public double getSurroundingFacilities() {
		return surroundingFacilities;
	}
	public void setSurroundingFacilities(double surroundingFacilities) {
		this.surroundingFacilities = surroundingFacilities;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getAccidentID() {
		return accidentID;
	}
	public void setAccidentID(String accidentID) {
		this.accidentID = accidentID;
	}
	public LocalDate getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(LocalDate accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	public int getDamagePer() {
		return damagePer;
	}
	public void setDamagePer(int damagePer) {
		this.damagePer = damagePer;
	}
	public String getKindOfCost() {
		return kindOfCost;
	}
	public void setKindOfCost(String kindOfCost) {
		this.kindOfCost = kindOfCost;
	}
	public boolean isPayCompleted() {
		return payCompleted;
	}
	public void setPayCompleted(boolean payCompleted) {
		this.payCompleted = payCompleted;
	}
	public int getLiablityRate() {
		return liablityRate;
	}
	public void setLiablityRate(int liablityRate) {
		this.liablityRate = liablityRate;
	}
	public int getLiablityCost() {
		return liablityCost;
	}
	public void setLiablityCost(int liablityCost) {
		this.liablityCost = liablityCost;
	}
	
}
