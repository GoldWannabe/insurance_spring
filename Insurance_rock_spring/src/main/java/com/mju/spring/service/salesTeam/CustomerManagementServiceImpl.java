package com.mju.spring.service.salesTeam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.CustomerDao;
import com.mju.spring.entity.Customer;

@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {
	
	private Customer customer;
	
	@Autowired
	CustomerDao customerDao;

	
	@Override
	public Customer getCustomerBasicInfo(HttpServletRequest request) {
		
		this.customer = new Customer();
		customer.setName(request.getParameter("customerName"));
		customer.setPhoneNum(request.getParameter("phoneNum"));
		
//		this.customerDao.retriveCustomerInfo(this.customer); 귀찮아아아아아
		
		return null;
	}

}
