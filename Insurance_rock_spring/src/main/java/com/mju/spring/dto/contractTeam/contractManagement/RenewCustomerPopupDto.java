package com.mju.spring.dto.contractTeam.contractManagement;

public class RenewCustomerPopupDto {
	private String customerName;
	private String insuranceName;
	private String customerPhoneNum;
	
	public String getCustomerPhoneNum() {
		return customerPhoneNum;
	}
	public void setCustomerPhoneNum(String customerPhoneNum) {
		this.customerPhoneNum = customerPhoneNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
}
