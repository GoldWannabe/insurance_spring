package com.mju.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.InsuranceDto;

public interface InsuranceSalesService {
	  
	public List<InsuranceDto> getInsuranceList(HttpServletRequest request);

	public InsuranceDto getInsurance(HttpServletRequest request);

	public boolean createContract(HttpServletRequest request);

	public boolean createCustomer(HttpServletRequest request);
	
	public boolean joinContractAndCustomer();

	

}
