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
	// customerID | Name | SSN       | Sex    | phoneNum    | address | bankName | accountNum | insuranceNum
		this.customer = this.customerManagementService.getCustomerBasicInfo(request); 
		model.addAttribute("CustomerName", this.customer.getName());
		model.addAttribute("SSN", this.customer.getSSN());
		model.addAttribute("PhoneNum", this.customer.getPhoneNum());
		model.addAttribute("Address", this.customer.getBankName());
		model.addAttribute("Sex", this.customer.getAccountNum());
		model.addAttribute("InsuranceNum", this.customer.getInsuranceNum());
		model.addAttribute("BankName", this.customer.getBankName());
		model.addAttribute("AccountNum", this.customer.getAccountNum());
		
		return "salesTeam//customerManagement//manageCustomer";
	}
	
	@RequestMapping(value = "manageCustomer", method = RequestMethod.GET)
	public String manageCustomer(HttpServletRequest request, Model model) {
		if(request.getParameter("manage").equals("update")) {
			this.customer = this.customerManagementService.getUpdateCustomerScrean(request); 
			model.addAttribute("CustomerName", this.customer.getName());
			model.addAttribute("SSN", this.customer.getSSN());
			model.addAttribute("PhoneNum", this.customer.getPhoneNum());
			model.addAttribute("Address", this.customer.getAddress());
			model.addAttribute("Sex", this.customer.getSex());
			model.addAttribute("InsuranceNum", this.customer.getInsuranceNum());
			model.addAttribute("BankName", this.customer.getBankName());
			model.addAttribute("AccountNum", this.customer.getAccountNum());
			return "salesTeam//customerManagement//updateCustomer";
		}else if(request.getParameter("manage").equals("add")) {
			
		}else if(request.getParameter("manage").equals("delete")) {
			
		}
			
		
		return "salesTeam//customerManagement//manageCustomer";
	}
	
	@RequestMapping(value = "updateCustomer", method = RequestMethod.GET)
	public String updateCustomer(HttpServletRequest request, Model model) {
		this.customerManagementService.updateCustomer(request);
		model.addAttribute("JudgeResult", "고객정보 수정이 완료되었습니다");
		
		return "menu//showResult";
	}
	
	
}
