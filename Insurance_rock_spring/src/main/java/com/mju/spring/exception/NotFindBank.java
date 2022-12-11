package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NotFindBank extends RuntimeException{
	public NotFindBank() {		
		super("시스템의 문제로 통장을 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
	}

}
