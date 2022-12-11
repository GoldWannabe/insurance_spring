package com.mju.spring.dao;

import com.mju.spring.dto.contractTeam.contractManagement.CustomerIDAndInsuranceNumDto;
import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;
import com.mju.spring.entity.Customer;

public interface CustomerDao {
	public CustomerBankDto retrivecustomerBank(String customerID);

	public Customer retriveCustomerById(String customerID);

	public Double selectInsuranceNum(String customerID);
	
	public int updateInsuranceNum(Customer customer);

	public void commit();

	public void updateInsuranceNum(CustomerIDAndInsuranceNumDto customerIDAndInsuranceNumDto);

	public int create(Customer customer);

	public void deleteInsuranceNum(String customerID);
	
	
}