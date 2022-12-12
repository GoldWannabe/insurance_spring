package com.mju.spring.service.salesTeam;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.entity.Customer;

public interface CustomerManagementService {

	public Customer getCustomerBasicInfo(HttpServletRequest request);

	public Customer getUpdateCustomerScrean(HttpServletRequest request);

	public void updateCustomer(HttpServletRequest request);

	public Customer addCustomer(HttpServletRequest request);
	

}
