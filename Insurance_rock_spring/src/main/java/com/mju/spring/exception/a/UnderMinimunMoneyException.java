package com.mju.spring.exception.a;

@SuppressWarnings("serial")
public class UnderMinimunMoneyException extends RuntimeException{
	public UnderMinimunMoneyException() {		
		super("통장 잔액이 1000원 이하입니다. 돈을 충전 후 다시 시도해 주세요.");
	}

}
