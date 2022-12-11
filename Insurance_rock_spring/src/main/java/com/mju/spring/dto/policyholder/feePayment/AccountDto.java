package com.mju.spring.dto.policyholder.feePayment;

public class AccountDto {

	private String bankName;
	private String accountNum;
	private boolean checkBank;
	private boolean checkCard;
	
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
	public boolean isCheckBank() {
		return checkBank;
	}
	public void setCheckBank(boolean checkBank) {
		this.checkBank = checkBank;
	}
	public boolean isCheckCard() {
		return checkCard;
	}
	public void setCheckCard(boolean checkCard) {
		this.checkCard = checkCard;
	}
	
}
