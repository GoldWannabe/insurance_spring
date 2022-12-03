package com.mju.spring.dto.contractTeam.Underwriting;

import java.time.LocalDate;

import com.mju.spring.entity.Rank;

public class VerifyRenewContractDto {
	
	private int previousInsuranceFee;
	private int newInsuranceFee;
	private int previousSecurityFee;
	public int getPreviousSecurityFee() {
		return previousSecurityFee;
	}
	public void setPreviousSecurityFee(int previousSecurityFee) {
		this.previousSecurityFee = previousSecurityFee;
	}
	public int getNewSecurityFee() {
		return newSecurityFee;
	}
	public void setNewSecurityFee(int newSecurityFee) {
		this.newSecurityFee = newSecurityFee;
	}
	private int newSecurityFee;
	
	private LocalDate endDate;
	private int period;
	private Rank previousRank;
	private Rank newRank;
	private double totalRank;
	
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
	public Rank getPreviousRank() {
		return previousRank;
	}
	public void setPreviousRank(Rank previousRank) {
		this.previousRank = previousRank;
	}
	public Rank getNewRank() {
		return newRank;
	}
	public void setNewRank(Rank newRank) {
		this.newRank = newRank;
	}
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
