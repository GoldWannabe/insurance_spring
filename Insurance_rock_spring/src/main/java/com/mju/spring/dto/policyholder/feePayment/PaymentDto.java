package com.mju.spring.dto.policyholder.feePayment;

import java.time.LocalDate;

import com.mju.spring.entity.Insurance.EInsurance;

public class PaymentDto {
//납부 기록(가입자명, 연락처, 보험이름, 보험종류, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다
	private String customerName;
	private String customerPhoneNum;
	private String insuranceName;
	private EInsurance insuranceType;
	private String cardOrBankName;
	private String accountNum;
	private int insuranceFee;
	private LocalDate paidDate;
	
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
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public EInsurance getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = EInsurance.valueOf(insuranceType);
	}
	public String getCardOrBankName() {
		return cardOrBankName;
	}
	public void setCardOrBankName(String cardOrBankName) {
		this.cardOrBankName = cardOrBankName;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public int getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public LocalDate getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}
	
	
}
