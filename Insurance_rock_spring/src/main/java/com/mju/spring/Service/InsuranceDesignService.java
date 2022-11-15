package com.mju.spring.Service;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.DTO.InsuranceDTO;

public interface InsuranceDesignService {

	public InsuranceDTO getinsuranceTypeAndTerm(HttpServletRequest request);

	public InsuranceDTO checkName(HttpServletRequest request);

	public InsuranceDTO checkRate();

	public boolean register();

	public boolean saveTempInsurance();

	public InsuranceDTO getTempInsurance();

	public InsuranceDTO getStandardFee(HttpServletRequest request);

}
