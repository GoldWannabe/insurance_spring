package com.mju.spring.service.salesTeam;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.entity.Customer;

public interface CustomerManagementService {

	Customer getCustomerBasicInfo(HttpServletRequest request);
	

}
