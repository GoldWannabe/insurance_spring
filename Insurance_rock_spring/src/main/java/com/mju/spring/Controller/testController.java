package com.mju.spring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.Service.InsuranceDesignService;

@Controller
public class testController {
	
	@Autowired
	InsuranceDesignService insuranceDesignService;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model) {
	
		
		insuranceDesignService.getinsuranceTypeAndTerm(request);
		System.out.println("끝1");
	
		insuranceDesignService.checkName(request);
		System.out.println("끝2");

		insuranceDesignService.checkRate(request);
		System.out.println("끝3");
		insuranceDesignService.getStandardFee();
		System.out.println("끝4");
		insuranceDesignService.register();
		System.out.println("끝5");
		return "test";
	}
	
	@RequestMapping(value = "test1", method = RequestMethod.GET)
	public String test1(HttpServletRequest request, Model model) {
		System.out.println("!@43567");
	
		
		return "test";
	}
	
	@RequestMapping(value = "insuranceType", method = RequestMethod.GET)
	public String insuranceType(HttpServletRequest request, Model model) {
		if (request.getParameter("next").equals("next")) {
			return "insuranceSales//salesTeam//inputInsuranceName";
		} else {
			return "error";
		}
		
	}
	@RequestMapping(value = "customerInfo", method = RequestMethod.GET)
	public String customerInfo(HttpServletRequest request, Model model) {
		if (request.getParameter("search").equals("search")) {
			return "insuranceSales//salesTeam//insuranceJoin";
		} else if(request.getParameter("menu").equals("cancellation")){
			return "menu";
		} else {
			return "error";
		}
	}
	@RequestMapping(value = "joinInsurance", method = RequestMethod.GET)
	public String joinInsurance(HttpServletRequest request, Model model) {
		if (request.getParameter("join").equals("insuranceJoin")) {
			return "insuranceSales//salesTeam//joinNewCustomer";
		} else if(request.getParameter("join").equals("insuranceReJoin")){
			return "insuranceSales//salesTeam//joinRe";
		} else if(request.getParameter("join").equals("cancellation")){
			return "menu";
		} else {
			return "error";
		}
		
	}
	@RequestMapping(value = "continuously", method = RequestMethod.GET)
	public String continuously(HttpServletRequest request, Model model) {
		if (request.getParameter("join").equals("joinRequest")) {
			return "insuranceSales//salesTeam//joinContinuously";
		} else if(request.getParameter("join").equals("cancellation")){
			return "menu";
		} else {
			return "error";
		}
		
	}
	@RequestMapping(value = "insuranceJoinFinish", method = RequestMethod.GET)
	public String insuranceJoinFinish(HttpServletRequest request, Model model) {
		if (request.getParameter("join").equals("joinRequest")) {
			return "menu";
		}else {
			return "error";
		}
		
	}
	@RequestMapping(value = "rejoin", method = RequestMethod.GET)
	public String rejoin(HttpServletRequest request, Model model) {
		if (request.getParameter("search").equals("search")) {
			return "insuranceSales//salesTeam//joinRe";//재가입신청 보류...
		}else {
			return "error";
		}
		
	}


	
}
