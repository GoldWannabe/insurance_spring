package com.mju.spring.Controller.SalesTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Service.InsuranceSalesService;

@Controller
public class InsuranceSalesController {
	
	@Autowired InsuranceSalesService insuranceSalesService;

	@RequestMapping(value = "selectInsuranceType", method = RequestMethod.GET)
	public String selectInsuranceType(HttpServletRequest request, Model model) {
		//보험 타입 정해서 해당 보험에 해당하는 리스트 리턴
		//getInsuranceList의 request에는 타입만 정의되어있다
		List<InsuranceDTO> insuranceList = this.insuranceSalesService.getInsuranceList(request); 
		model.addAttribute("InsuranceList", insuranceList);
		
		return "salesTeam//insuranceSales//inputInsuranceName";
	}
	

	@RequestMapping(value = "inputInsuranceName", method = RequestMethod.GET)
	public String selectInsurance(HttpServletRequest request, Model model) {
		//선택한 보험 상세정보 리턴
		//getInsurance의 request에는 이름과 타입이 정의되어있다
		
		InsuranceDTO insuranceDTO = this.insuranceSalesService.getInsurance(request);
		model.addAttribute("LongTerm", insuranceDTO.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("InsuranceType", insuranceDTO.getInsuranceType());
		model.addAttribute("InsuranceName", insuranceDTO.getInsuranceName());
		model.addAttribute("SpecialContract", insuranceDTO.getSpecialContract());
		model.addAttribute("ApplyCondition", insuranceDTO.getApplyCondition());
		model.addAttribute("CompensateCondition", insuranceDTO.getCompensateCondition());
		model.addAttribute("Explanation", insuranceDTO.getExplanation());
		model.addAttribute("PremiumRate", insuranceDTO.getPremiumRate());
		model.addAttribute("StandardFee", insuranceDTO.getStandardFee());


		return "salesTeam//insuranceSales//joinSelection";
	}
	
	@RequestMapping(value = "joinSelection", method = RequestMethod.GET)
	public String joinSelection(HttpServletRequest request, Model model) {
		//보험 가입, 재가입 여부 선택
		if(request.getParameter("join").equals("join")) {
			return "salesTeam//insuranceSales//join";
		} else if(request.getParameter("join").equals("reJoin")) {
			return "salesTeam//insuranceSales//insuranceReJoin";
		} else if(request.getParameter("join").equals("cancel")) {
			return "menu";
		} else {
			return "error";
		}

	}
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join(HttpServletRequest request, Model model) {

		//일단은 여기서 계약과 고객 생성
		if(this.insuranceSalesService.createCustomer(request) && this.insuranceSalesService.createApplyContract(request)) {
			return joinApplyContractAndCustomer(model);
		} else {
			return "error";
		}
 

		
	}
	
	public String joinApplyContractAndCustomer(Model model) {
		if(this.insuranceSalesService.joinApplyContractAndCustomer()) {
			model.addAttribute("Finish", true);
			return "salesTeam//insuranceSales//join";
		} else {
			return "error";
		}

	}
//	@RequestMapping(value = "inputInsuranceName", method = RequestMethod.GET)
//	public String selectInsurance(HttpServletRequest request, Model model) {
		//선택한 보험 상세정보 리턴
//		//getInsurance의 request에는 이름과 타입이 정의되어있다
//		InsuranceDTO insuranceDTO = this.insuranceSalesService.getInsurance(request);
//		model.addAttribute("LongTerm", insuranceDTO.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
//		model.addAttribute("InsuranceType", insuranceDTO.getInsuranceType());
//		model.addAttribute("StandardFee", insuranceDTO.getStandardFee());
//		model.addAttribute("InsuranceName", insuranceDTO.getInsuranceName());
//		model.addAttribute("SpecialContract", insuranceDTO.getSpecialContract());
//		model.addAttribute("ApplyCondition", insuranceDTO.getApplyCondition());
//		model.addAttribute("CompensateCondition", insuranceDTO.getCompensateCondition());
//		model.addAttribute("Explanation", insuranceDTO.getExplanation());
//		model.addAttribute("PremiumRate", insuranceDTO.getPremiumRate());
//
//
//		return "salesTeam//insuranceSales//joinSelection";
//	}
//	@RequestMapping(value = "joinSelection", method = RequestMethod.GET)
//	public String joinSelection(HttpServletRequest request, Model model) {
//		//보험 가입, 재가입 여부 선택
//		if(request.getParameter("join").equals("join")) {
//			return "salesTeam//insuranceSales//join";
//		} else if(request.getParameter("join").equals("reJoin")) {
//			return "salesTeam//insuranceSales//insuranceReJoin";
//		} else if(request.getParameter("join").equals("cancel")) {
//			return "menu";
//		} else {
//			return "error";
//		}
//
//	}
//	@RequestMapping(value = "join", method = RequestMethod.GET)
//	public String join(HttpServletRequest request, Model model) {
//
//		//일단은 여기서 계약과 고객 생성
//		if(this.insuranceSalesController.createContract(request) && this.insuranceSalesController.createCustomer(request)) {
//			return register(model);
//		} else {
//			return "error";
//		}
// 
//
//		
//	}
//	public String register(Model model) {
//		if(this.service.register()) {
//			model.addAttribute("Finish", true);
//			return "salesTeam//insuranceSales//join";
//		} else {
//			return "error";
//		}
//
//	}

	@RequestMapping(value = "finishSales", method = RequestMethod.GET)
	public String finishSales(HttpServletRequest request, Model model) {
		return "menu";
	}
}
