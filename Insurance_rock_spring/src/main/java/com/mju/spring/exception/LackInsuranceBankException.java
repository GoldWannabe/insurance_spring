package com.mju.spring.exception;

@SuppressWarnings("serial")
public class LackInsuranceBankException extends RuntimeException{
	public LackInsuranceBankException() {		
		super("보험 통장의 잔액이 부족합니다. 확인후 다시 진행해 주세요");
	}

}
