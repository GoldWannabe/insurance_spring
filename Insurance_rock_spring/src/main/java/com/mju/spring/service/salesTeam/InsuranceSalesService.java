package com.mju.spring.service.salesTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.InsuranceDto;

public interface InsuranceSalesService {
	  
	public List<InsuranceDto> getInsuranceList(HttpServletRequest request);

	public InsuranceDto getInsurance(HttpServletRequest request);


	public boolean createCustomer(HttpServletRequest request);
	
	public boolean joinApplyContractAndCustomer();

	public boolean createApplyContract(HttpServletRequest request);

	

}
