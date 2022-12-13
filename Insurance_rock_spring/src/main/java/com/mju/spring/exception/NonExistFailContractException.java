package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NonExistFailContractException extends RuntimeException{
	public NonExistFailContractException() {		
		super("해당고객의 가입 실패 내역이 존재하지않습니다. 다시입력해주세요");
	}

}
