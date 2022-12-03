package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.CustomerRankDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;

public interface CustomerRankDao {
	public int create(CustomerRankDto customerRankDTO);

	public void commit();

	public RenewCustomerRankDto retriveAllId(String customerID);

	public String retriveRankID(String contractID);
}
