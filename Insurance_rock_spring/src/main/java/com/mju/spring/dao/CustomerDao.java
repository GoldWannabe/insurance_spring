package com.mju.spring.dao;

import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;

@Repository
public interface CustomerDao {
	public CustomerBankDto retrivecustomerBank(String customerID);
	
}
