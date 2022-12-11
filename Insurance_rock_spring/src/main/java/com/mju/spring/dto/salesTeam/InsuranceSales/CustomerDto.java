package com.mju.spring.dto.salesTeam.InsuranceSales;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mju.spring.entity.Rank;


public class CustomerDto {
	
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

	private enum Esex {
		male, female, none,
	};


	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
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
	}

	public Double getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(Double insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public String getSex() {
		return sex.toString();
	}

	public void setSex(String sex) {
		// set logic
		if (sex.equals(Esex.male.toString())) {
			this.sex = Esex.male;
		} else if (sex.equals(Esex.female.toString())) {
			this.sex = Esex.female;
		} else if (sex.equals(Esex.none.toString())) {
			this.sex = Esex.none;
		}
	}

	public String toStringContract() {
		if (!this.contractID.equals(null)) {
			String returnID = String.join("-", this.contractID);
			return returnID;
		}
		return "null";
	}

	public String toStringRankID() {
		if (!this.rankID.equals(null)) {
			String returnID = String.join(" ", this.rankID);
			return returnID;
		}
		return "null";
	}


	public boolean addCustomerIDRankID(String contractID, String rankID) {
		return (this.contractID.add(contractID) && this.rankID.add(rankID));

	}


	private boolean setRank(ResultSet resultSet) {

		try {
			resultSet.next();
			this.rank.setRankID(resultSet.getString("RankID"));
			this.rank.setMaterial(resultSet.getString("material"));
			this.rank.setFireFacilities(resultSet.getDouble("fireFacilities"));
			this.rank.setHeight(resultSet.getBoolean("height"));
			this.rank.setScale(resultSet.getInt("scale"));
			this.rank.setSurroundingFacilities(resultSet.getFloat("surroundingFacilities"));
			this.rank.setPurpose(resultSet.getString("purpose"));

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}


	public Rank getRank() {
		
		return this.rank;
	}


}
