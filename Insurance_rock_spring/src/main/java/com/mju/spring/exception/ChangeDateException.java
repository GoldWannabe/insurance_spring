package com.mju.spring.exception;

@SuppressWarnings("serial")
public class ChangeDateException extends RuntimeException{
	public ChangeDateException() {		
		super("날짜가 변경되어 정보가 수정되었습니다. 시스템을 종료하니 재접속 바랍니다.");
	}

}
