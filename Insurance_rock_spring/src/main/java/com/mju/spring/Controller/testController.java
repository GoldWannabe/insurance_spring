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
		
	
		insuranceDesignService.checkName(request);
		

		insuranceDesignService.checkRate(request);
		insuranceDesignService.getStandardFee();
		insuranceDesignService.register();
		System.out.println("끝");
		return "test";
	}
	
	@RequestMapping(value = "test1", method = RequestMethod.GET)
	public String test1(HttpServletRequest request, Model model) {
		System.out.println("!@43567");
	
		
		return "test";
	}
}
