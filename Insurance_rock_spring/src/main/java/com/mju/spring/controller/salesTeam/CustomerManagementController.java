package com.mju.spring.controller.salesTeam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.entity.Customer;
import com.mju.spring.service.salesTeam.CustomerManagementService;

@Controller
public class CustomerManagementController {
	
	private Customer customer;
	
	@Autowired CustomerManagementService customerManagementService;
	
	@RequestMapping(value = "inputCustomerBasicInfo", method = RequestMethod.GET)
	public String inputCustomerBasicInfo(HttpServletRequest request, Model model) {
		
		this.customer = this.customerManagementService.getCustomerBasicInfo(request); 
//		model.addAttribute("InsuranceList", insuranceList);
		
		return "salesTeam//customerManagement//manageCustomer";
	}
	
}
