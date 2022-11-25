package com.mju.spring.controller.financialDirector;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto;
import com.mju.spring.entity.Insurance;
import com.mju.spring.service.financialDirector.InsuranceJudgeService;

@Controller
public class InsuranceJudgeController {
	
	@Autowired InsuranceJudgeService insuranceJudgeService;
	
	@RequestMapping(value = "startJudge", method = RequestMethod.GET)
	public String startJudge(HttpServletRequest request, Model model) {
		//모든 보험 가져와서 리턴
		if(request.getParameter("menu").equals("start")) {
		List<RegisterInsuranceDto> registerInsuranceDaoList = this.insuranceJudgeService.getRegisterInsurance();
		model.addAttribute("registerInsuranceList", registerInsuranceDaoList);
		return "financialDirector//insuranceJudge//selectJudgeInsurance";
		} else if(request.getParameter("menu").equals("cancel")){
			return "menu";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(value = "selectJudgeInsurance", method = RequestMethod.GET)
	public String selectJudgeInsurance(HttpServletRequest request, Model model) {
		//선택한 보험 가져와서 리턴
		Insurance insurance = this.insuranceJudgeService.selectJudgeInsurance(request);
		model.addAttribute("insurance", insurance); //요율은 따로 보여줘야 할 수도
		System.out.println("이예예예예예예예옝");
		return "financialDirector//insuranceJudge//selectPermitInsurance";
	}
	
	

	@RequestMapping(value = "selectPermitInsurance", method = RequestMethod.GET)
	public String selectPermitInsurance(HttpServletRequest request, Model model) {
		//선택에 따른 승인 비승인 보류
		return "menu";
	}
}
