package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NonCustomerContractException extends RuntimeException{
	public NonCustomerContractException() {		
		super("이 보험에 계약된 고객이 조회되지 않습니다.");
	}

}
