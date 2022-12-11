package com.mju.spring.dto.policyholder.feePayment;

public class ContractAccountDto {

	private String contractID;
	private int unpaidFee;
	
	
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public int getUnpaidFee() {
		return unpaidFee;
	}
	public void setUnpaidFee(int unpaidFee) {
		this.unpaidFee = unpaidFee;
	}

	
}
