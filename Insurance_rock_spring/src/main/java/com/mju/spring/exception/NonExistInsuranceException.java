package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NonExistInsuranceException extends RuntimeException{
	public NonExistInsuranceException() {		
		super("조회된 보험이 없습니다. 정확한 검색어를 입력하여 주십시오.");
	}
}
