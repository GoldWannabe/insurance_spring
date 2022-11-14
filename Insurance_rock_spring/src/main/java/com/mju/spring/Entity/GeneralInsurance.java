package com.mju.spring.Entity;

public class GeneralInsurance  extends Insurance{

	private int generalPremiumRate;
	private double[] premiumRate = new double[] { 0.002, 0.004, 0.006 };
	
	public int getGeneralPremiumRate() {
		return generalPremiumRate;
	}
	public void setGeneralPremiumRate(int generalPremiumRate) {
		this.generalPremiumRate = generalPremiumRate;
	}
	public double[] getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(double[] premiumRate) {
		this.premiumRate = premiumRate;
	}
	
}
