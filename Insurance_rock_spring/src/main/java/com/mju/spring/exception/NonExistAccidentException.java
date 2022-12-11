package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NonExistAccidentException extends RuntimeException{
	public NonExistAccidentException() {		
		super("사고 정보를 찾지 못했습니다. 사고날짜와 가입자명을 오탈자 없이 적어주세요.");
	}

}
