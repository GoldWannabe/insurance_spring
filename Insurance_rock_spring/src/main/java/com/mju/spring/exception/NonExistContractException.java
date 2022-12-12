package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NonExistContractException extends RuntimeException{
	public NonExistContractException() {		
		super("해당고객이 가입한 보험이 존재하지않습니다. 다시 입력해주세요");
	}

}
