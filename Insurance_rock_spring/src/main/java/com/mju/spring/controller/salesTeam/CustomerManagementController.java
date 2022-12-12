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
			return "salesTeam//customerManagement//addCustomer";
			
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
	@RequestMapping(value = "addCustomer", method = RequestMethod.GET)
	public String addCustomer(HttpServletRequest request, Model model) {
		Customer customer = this.customerManagementService.addCustomer(request);
		if(customer != null) {
			model.addAttribute("JudgeResult", "고객정보가 추가되었습니다");
			return "menu//showResult";
			
		}else {
			//E2.고객과 전화번호가 같은 고객이 있을 경우
			model.addAttribute("Popup", true);
			model.addAttribute("Message", "수정한 고객의 이름과 연락처가 같은 고객이 고객DB에 존재합니다. 고객 정보 수정 또는 추가가 되지 않았습니다.");
			return "salesTeam//customerManagement//addCustomer";
		}
		
	}
	
	
}
