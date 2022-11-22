package com.mju.spring.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.DTO.InsuranceDTO;

public interface InsuranceSalesService {
	  
	public List<InsuranceDTO> getInsuranceList(HttpServletRequest request);

	public InsuranceDTO getInsurance(HttpServletRequest request);

	public boolean createContract(HttpServletRequest request);

	public boolean createCustomer(HttpServletRequest request);
	
	public boolean joinContractAndCustomer();

	

}
