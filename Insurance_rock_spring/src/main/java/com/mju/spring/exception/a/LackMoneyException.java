package com.mju.spring.exception.a;

@SuppressWarnings("serial")
public class LackMoneyException extends RuntimeException{
	public LackMoneyException() {		
		super("잔액이 부족합니다");
	}

}
