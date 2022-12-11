package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NotFindJudgeContractException extends RuntimeException{
	public NotFindJudgeContractException() {
		super("심사할 계약이 없습니다");
		
	}
}
