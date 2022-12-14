package com.mju.spring.service.salesTeam;

import java.util.Random;
import java.util.UUID;

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
		
		this.customer = this.customerDao.retriveCustomerInfo(this.customer);
		return customer;
	}


	@Override
	public Customer getUpdateCustomerScrean(HttpServletRequest request) {
		return this.customer;
	}


	@Override
	public void updateCustomer(HttpServletRequest request) {
		customer.setCustomerID(this.customer.getCustomerID());
		customer.setName(request.getParameter("customerName"));
		customer.setSSN(request.getParameter("ssn"));
		customer.setPhoneNum(request.getParameter("phoneNum"));
		customer.setAddress(request.getParameter("address"));
		customer.setSex(request.getParameter("gender"));
		customer.setInsuranceNum(Double.valueOf(request.getParameter("insuranceNum")));
		customer.setBankName(request.getParameter("bankName"));
		customer.setAccountNum(request.getParameter("accountNum"));
		this.customerDao.updateCustomerInfo(this.customer);
		
	}


	@Override
	public Customer addCustomer(HttpServletRequest request) {
		if(!(this.customer.getName().equals(request.getParameter("customerName"))) && !(this.customer.getPhoneNum().equals(request.getParameter("phoneNum")))){
			
			customer.setCustomerID(UUID.randomUUID().toString().substring(0, 5));
			customer.setName(request.getParameter("customerName"));
			customer.setSSN(request.getParameter("ssn"));
			customer.setPhoneNum(request.getParameter("phoneNum"));
			customer.setAddress(request.getParameter("address"));
			customer.setSex(request.getParameter("gender"));
			customer.setInsuranceNum(Double.valueOf(request.getParameter("insuranceNum")));
			customer.setBankName(request.getParameter("bankName"));
			customer.setAccountNum(request.getParameter("accountNum"));
			this.customerDao.create(this.customer);
			return this.customer;
		}else {
			return null;
		}
	}

}
