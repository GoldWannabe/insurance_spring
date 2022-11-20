package com.mju.spring.Controller.SalesTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.DTO.InsuranceDTO;

@Controller
public class InsuranceSalesController {
	
	@Autowired Service service;

	@RequestMapping(value = "selectSalesType", method = RequestMethod.GET)
	public String selectInsuranceType(HttpServletRequest request, Model model) {
		//보험 타입 정해서 해당 보험에 해당하는 리스트 리턴
		this.service.selectInsuranceType(request);
		List<InsuranceDTO> insuranceList = this.service.getInsuranceList();
		model.addAttribute("InsuranceList", insuranceList);

		return "selectInsurance";
	}
	
	@RequestMapping(value = "selectInsurance", method = RequestMethod.GET)
	public String selectInsurance(HttpServletRequest request, Model model) {
		//선택한 보험 상세정보 리턴
		InsuranceDTO insuranceDTO = this.service.getInsurance(request);
		model.addAttribute("LongTerm", insuranceDTO.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("InsuranceType", insuranceDTO.getInsuranceType());
		model.addAttribute("InsuranceName", insuranceDTO.getInsuranceName());
		model.addAttribute("SpecialContract", insuranceDTO.getSpecialContract());
		model.addAttribute("ApplyCondition", insuranceDTO.getApplyCondition());
		model.addAttribute("CompensateCondition", insuranceDTO.getCompensateCondition());
		model.addAttribute("Explanation", insuranceDTO.getExplanation());
		model.addAttribute("PremiumRate", insuranceDTO.getPremiumRate());


		return "";
	}
	@RequestMapping(value = "selectContract", method = RequestMethod.GET)
	public String selectContract(HttpServletRequest request, Model model) {
		//보험 가입, 재가입 여부 선택
		if(request.getParameter("").equals("가입")) {
			return "가입";
		} else if(request.getParameter("").equals("재가입")) {
			return "재가입";
		} else {
			return "error";
		}

	}
	@RequestMapping(value = "createContractAndCustomer", method = RequestMethod.GET)
	public String createContractAndCustomer(HttpServletRequest request, Model model) {
		//일단은 여기서 계약과 고객 생성
		if(this.service.createContract && this.service.createCustomer) {
			return register();
		} else {
			return "error";
		}
 

		
	}
	public String register(HttpServletRequest request, Model model) {
		if(this.service.register()) {
			return "가입 완료 창";
		} else {
			return "error";
		}

	}

}
