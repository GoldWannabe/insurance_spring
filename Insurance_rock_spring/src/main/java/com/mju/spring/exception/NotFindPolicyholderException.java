package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NotFindPolicyholderException extends RuntimeException{
	public NotFindPolicyholderException() {		
		super("해당 고객이 없습니다. 문제가 있을 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
	}

}
