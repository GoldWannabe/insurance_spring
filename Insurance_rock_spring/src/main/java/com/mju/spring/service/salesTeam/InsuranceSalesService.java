package com.mju.spring.service.salesTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Insurance;

public interface InsuranceSalesService {
	  
	public List<Insurance> getInsuranceList(HttpServletRequest request);

	public Insurance getInsurance(HttpServletRequest request);

	public boolean createCustomer(HttpServletRequest request);
	
	public boolean joinApplyContractAndCustomer();

	public boolean createApplyContract(HttpServletRequest request);

	public List<Contract> searchFailContract(HttpServletRequest request);

	public Contract selectFailContract(HttpServletRequest request);

	public boolean rejoin(HttpServletRequest request);

	

}
