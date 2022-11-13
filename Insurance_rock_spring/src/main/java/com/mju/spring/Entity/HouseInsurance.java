package com.mju.spring.Entity;

public class HouseInsurance extends Insurance {
	
	private int housePremiumRate;
	private double[] premiumRate = new double[] { 0.002, 0.003, 0.005 };
	
	public int getHousePremiumRate() {
		return housePremiumRate;
	}
	public void setHousePremiumRate(int housePremiumRate) {
		this.housePremiumRate = housePremiumRate;
	}
	public double[] getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(double[] premiumRate) {
		this.premiumRate = premiumRate;
	}
	
	

}
