package com.mju.spring.service;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.InsuranceDto;

public interface InsuranceDesignService {

	public InsuranceDto getinsuranceTypeAndTerm(HttpServletRequest request);

	public InsuranceDto checkName(HttpServletRequest request);

	public InsuranceDto checkRate(HttpServletRequest request);

	public boolean register();

	public boolean saveTempInsurance();

	public InsuranceDto getTempInsurance(HttpServletRequest request);

	public InsuranceDto getStandardFee();

}
