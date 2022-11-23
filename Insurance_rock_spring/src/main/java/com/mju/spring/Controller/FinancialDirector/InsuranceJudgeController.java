package com.mju.spring.Controller.FinancialDirector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.Service.InsuranceJudgeService;

@Controller
public class InsuranceJudgeController {
	
	@Autowired InsuranceJudgeService insuranceJudgeService;
	
	@RequestMapping(value = "startJudge", method = RequestMethod.GET)
	public String startJudge(HttpServletRequest request, Model model) {
		//모든 보험 가져와서 리턴
		return "selectJudgeInsurance";
	}
	
	@RequestMapping(value = "selectJudgeInsurance", method = RequestMethod.GET)
	public String selectJudgeInsurance(HttpServletRequest request, Model model) {
		//선택한 보험 가져와서 리턴
		return "inancialDirector//insuranceJudge//selectPermitInsurance";
	}
	
	

	@RequestMapping(value = "selectPermitInsurance", method = RequestMethod.GET)
	public String selectPermitInsurance(HttpServletRequest request, Model model) {
		//선택에 따른 승인 비승인 보류
		return "menu";
	}
}
