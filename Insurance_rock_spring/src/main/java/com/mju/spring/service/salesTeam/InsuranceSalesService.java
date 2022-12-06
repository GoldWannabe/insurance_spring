package com.mju.spring.service.salesTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.entity.Insurance;

public interface InsuranceSalesService {
	  
	public List<Insurance> getInsuranceList(HttpServletRequest request);

	public Insurance getInsurance(HttpServletRequest request);

	public boolean createCustomer(HttpServletRequest request);
	
	public boolean joinApplyContractAndCustomer();

	public boolean createApplyContract(HttpServletRequest request);

	

}
