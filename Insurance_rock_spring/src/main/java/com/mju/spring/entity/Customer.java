package com.mju.spring.entity;

import java.util.ArrayList;

public class Customer {

	private String customerID;
	private String name;
	private String SSN;
	private Esex sex;
	private String phoneNum;
	private String address;
	private String bankName;
	private String accountNum;
	private Double insuranceNum;

	private Rank rank;
	private ArrayList<String> contractID = new ArrayList<String>();
	private ArrayList<String> rankID = new ArrayList<String>();

	public enum Esex {
		male, female, none,
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public Esex getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = Esex.valueOf(sex);
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Double getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(Double insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public ArrayList<String> getContractID() {
		return contractID;
	}

	public void setContractID(ArrayList<String> contractID) {
		this.contractID = contractID;
	}

	public ArrayList<String> getRankID() {
		return rankID;
	}

	public void setRankID(ArrayList<String> rankID) {
		this.rankID = rankID;
	};

}
