package com.mju.spring.entity;

import java.time.LocalDate;

public class Accident {
	private String accidentID;
	private String contractID;
	private String customerID;
	private String customerName;
	private String customerPhoneNum;
	private LocalDate accidentDate;
	private String content;
	private int totalCost;
	private int damagePer;
	private String kindOfCost;
	private boolean payCompleted;
	private int liablityRate;
	private int liablityCost;
	public String getAccidentID() {
		return accidentID;
	}
	public void setAccidentID(String accidnetID) {
		this.accidentID = accidnetID;
	}
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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
