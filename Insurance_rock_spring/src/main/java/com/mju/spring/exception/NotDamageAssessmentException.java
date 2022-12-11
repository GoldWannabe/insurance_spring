package com.mju.spring.exception;

@SuppressWarnings("serial")
public class NotDamageAssessmentException extends RuntimeException{
	public NotDamageAssessmentException() {		
		super("손해사정이 완료 되지않았습니다.");
	}

}
