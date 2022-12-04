package com.mju.spring.dto.contractTeam.Underwriting;

import com.mju.spring.entity.Rank;

public class VerifyApplyContractDto {
	
	private int insuranceFee;
	private int standardFee;
	private int securityFee;
	private Rank rank;
	private int period;
	private int paymentCycle;
	private String applyCondition;
	private int totalRank;
	
	public int getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public int getStandardFee() {
		return standardFee;
	}
	public void setStandardFee(int standardFee) {
		this.standardFee = standardFee;
	}
	public int getSecurityFee() {
		return securityFee;
	}
	public void setSecurityFee(int securityFee) {
		this.securityFee = securityFee;
	}
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getPaymentCycle() {
		return paymentCycle;
	}
	public void setPaymentCycle(int paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	public String getApplyCondition() {
		return applyCondition;
	}
	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}
	public int getTotalRank() {
		return totalRank;
	}
	public void setTotalRank(int totalRank) {
		this.totalRank = totalRank;
	}

	
}
