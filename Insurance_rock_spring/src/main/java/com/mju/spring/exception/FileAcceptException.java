package com.mju.spring.exception;

@SuppressWarnings("serial")
public class FileAcceptException extends RuntimeException {
	public FileAcceptException() {
		super("통장에 문제가 생겼습니다. 관련 팀(1234-5678)에 최대한 빠르게 연락바랍니다.");
		
	}
}
