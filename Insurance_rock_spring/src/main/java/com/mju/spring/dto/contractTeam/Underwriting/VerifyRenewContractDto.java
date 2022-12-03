package com.mju.spring.dto.contractTeam.Underwriting;

import java.time.LocalDate;

public class VerifyRenewContractDto {
	
	private int previousInsuranceFee;
	private int newInsuranceFee;
	
//	private Rank previousRank;
//	private Rank newRank;
	
	
	private double totalRank;
	private LocalDate endDate;
	private int period;
	public int getPreviousInsuranceFee() {
		return previousInsuranceFee;
	}
	public void setPreviousInsuranceFee(int previousInsuranceFee) {
		this.previousInsuranceFee = previousInsuranceFee;
	}
	public int getNewInsuranceFee() {
		return newInsuranceFee;
	}
	public void setNewInsuranceFee(int newInsuranceFee) {
		this.newInsuranceFee = newInsuranceFee;
	}
//	public Rank getPreviousRank() {
//		return previousRank;
//	}
//	public void setPreviousRank(Rank previousRank) {
//		this.previousRank = previousRank;
//	}
//	public Rank getNewRank() {
//		return newRank;
//	}
//	public void setNewRank(Rank newRank) {
//		this.newRank = newRank;
//	}
	public double getTotalRank() {
		return totalRank;
	}
	public void setTotalRank(double totalRank) {
		this.totalRank = totalRank;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	
	
}
