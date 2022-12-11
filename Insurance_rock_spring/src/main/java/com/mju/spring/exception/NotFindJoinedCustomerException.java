package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NotFindJoinedCustomerException extends RuntimeException{
	public NotFindJoinedCustomerException() {
		super("시스템의 문제로 고객 명단 확인이 어렵습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		
	}
}
