package com.mju.spring.exception;

@SuppressWarnings("serial")
public class OverlapInsuranceNameException extends RuntimeException{
	public OverlapInsuranceNameException() {		
		super("기존의 보험이름과 중복 됩니다. 다른 이름을 정해주세요.(혹은 심사대기중인 보험이름과 중복)");
	}

}
