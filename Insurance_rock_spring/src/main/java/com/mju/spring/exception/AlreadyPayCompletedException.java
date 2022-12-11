package com.mju.spring.exception;

@SuppressWarnings("serial")
public class AlreadyPayCompletedException extends RuntimeException{
	public AlreadyPayCompletedException() {		
		super("이미 보상금이 지급 완료되었습니다.");
	}

}
