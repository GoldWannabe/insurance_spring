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
	@RequestMapping(value = "insuranceMarketing", method = RequestMethod.GET)
	public String ShowInsuranceMarketing(HttpServletRequest request, Model model) {
		
		if (request.getParameter("insuranceMarketing").equals("insuranceSales")) {
			return "salesTeam/slectInsuranceType";
		} else if (request.getParameter("insuranceMarketing").equals("customerManagement")) {
			return "salesTeam/";
		} else if (request.getParameter("insuranceMarketing").equals("channelManagement")) {
			return "salesTeam/";
		} else {
			return "error";
		}
		
	}

	
}
