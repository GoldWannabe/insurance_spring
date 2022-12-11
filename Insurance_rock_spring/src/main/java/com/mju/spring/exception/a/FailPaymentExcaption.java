package com.mju.spring.exception.a;

@SuppressWarnings("serial")
public class FailPaymentExcaption extends RuntimeException{
	public FailPaymentExcaption() {		
		super("결제가 실패 했습니다. 만약 통장 잔액에 문제가 있다면 고객센터(1234-5678)로 연락주시기 바랍니다.");
	}

}
