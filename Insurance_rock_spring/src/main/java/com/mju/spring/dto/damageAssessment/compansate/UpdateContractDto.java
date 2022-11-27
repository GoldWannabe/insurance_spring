package com.mju.spring.dto.damageAssessment.compansate;

public class UpdateContractDto {
	private String contractID;
	private int provisionFee;
	private int liablityCost;
	
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public int getProvisionFee() {
		return provisionFee;
	}
	public void setProvisionFee(int provisionFee) {
		this.provisionFee = provisionFee;
	}
	public int getLiablityCost() {
		return liablityCost;
	}
	public void setLiablityCost(int liablityCost) {
		this.liablityCost = liablityCost;
	}
}
