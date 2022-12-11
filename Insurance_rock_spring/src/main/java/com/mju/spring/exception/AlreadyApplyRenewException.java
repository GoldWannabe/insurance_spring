package com.mju.spring.exception;

@SuppressWarnings("serial")
public class AlreadyApplyRenewException extends RuntimeException{
	public AlreadyApplyRenewException() {		
		super("해당 계약은 이미 갱신 신청 접수가 되어 갱신 혹은  해지할수없습니다. 다른 계약을 선택하시거나 고객센터(010-1234-5678)로 문의주기실 바랍니다.");
	}

}
