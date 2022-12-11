package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NotFindJudgedInsuranceException extends RuntimeException {
	public NotFindJudgedInsuranceException() {
		super("심사할 보험이 존재하지 않습니다.");
	}

}
