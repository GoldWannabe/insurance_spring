package com.mju.spring.Controller.SalesTeam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class InsuranceSalesController {

	@RequestMapping(value = "selectSalesType", method = RequestMethod.GET)
	public String selectInsuranceType(HttpServletRequest request, Model model) {



		return "";
	}
}
