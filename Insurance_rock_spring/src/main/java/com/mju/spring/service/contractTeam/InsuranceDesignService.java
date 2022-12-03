package com.mju.spring.service.contractTeam;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.InsuranceDto;
import com.mju.spring.dto.InsuranceTypeAndTermDto;
import com.mju.spring.entity.Insurance;

public interface InsuranceDesignService {

	public InsuranceTypeAndTermDto getinsuranceTypeAndTerm(HttpServletRequest request);

	public Insurance checkName(HttpServletRequest request);

	public Insurance checkRate(HttpServletRequest request);

	public boolean register();

	public boolean saveTempInsurance();

	public Insurance getTempInsurance(HttpServletRequest request);

	public Insurance getStandardFee();

}
