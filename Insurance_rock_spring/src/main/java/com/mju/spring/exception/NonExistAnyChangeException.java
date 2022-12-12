package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NonExistAnyChangeException extends RuntimeException{
	public NonExistAnyChangeException() {		
		super("수정사항이 없습니다. 재신청을 위해 달라진 정보를 입력하여 주십시오.");
		
	}

}
