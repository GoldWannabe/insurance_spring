package com.mju.spring.dao;

import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;
import com.mju.spring.entity.Customer;

public interface CustomerDao {
	public CustomerBankDto retrivecustomerBank(String customerID);

	public Customer retriveCustomerById(String customerID);
	
}
