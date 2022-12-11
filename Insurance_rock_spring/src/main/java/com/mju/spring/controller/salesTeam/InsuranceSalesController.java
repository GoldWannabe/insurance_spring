package com.mju.spring.controller.salesTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Insurance;
import com.mju.spring.service.salesTeam.InsuranceSalesService;

@Controller
public class InsuranceSalesController {

	@Autowired
	InsuranceSalesService insuranceSalesService;

	@RequestMapping(value = "selectInsuranceType", method = RequestMethod.GET)
	public String selectInsuranceType(HttpServletRequest request, Model model) {
		// 보험 타입 정해서 해당 보험에 해당하는 리스트 리턴
		// getInsuranceList의 request에는 타입만 정의되어있다
		List<Insurance> insuranceList = this.insuranceSalesService.getInsuranceList(request);
		model.addAttribute("InsuranceList", insuranceList);

		return "salesTeam//insuranceSales//inputInsuranceName";
	}

	@RequestMapping(value = "inputInsuranceName", method = RequestMethod.GET)
	public String selectInsurance(HttpServletRequest request, Model model) {
		// 선택한 보험 상세정보 리턴
		// getInsurance의 request에는 이름과 타입이 정의되어있다
		// Insurance
		Insurance insurance = this.insuranceSalesService.getInsurance(request);
		model.addAttribute("LongTerm", insurance.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("InsuranceType", insurance.getInsuranceType());
		model.addAttribute("InsuranceName", insurance.getInsuranceName());
		model.addAttribute("SpecialContract", insurance.getSpecialContract());
		model.addAttribute("ApplyCondition", insurance.getApplyCondition());
		model.addAttribute("CompensateCondition", insurance.getCompensateCondition());
		model.addAttribute("Explanation", insurance.getExplanation());
		model.addAttribute("PremiumRate", insurance.getPremiumRate());
		model.addAttribute("StandardFee", insurance.getStandardFee());

		return "salesTeam//insuranceSales//joinSelection";
	}

	@RequestMapping(value = "joinSelection", method = RequestMethod.GET)
	public String joinSelection(HttpServletRequest request, Model model) {
		// 보험 가입, 재가입 여부 선택
		if (request.getParameter("join").equals("join")) {
			return "salesTeam//insuranceSales//join";
		} else if (request.getParameter("join").equals("rejoin")) {
			return "salesTeam//insuranceSales//rejoin";
		} else if (request.getParameter("join").equals("cancel")) {
			return "menu";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join(HttpServletRequest request, Model model) {
		// 일단은 여기서 계약과 고객 생성
		if (this.insuranceSalesService.createCustomer(request)
				&& this.insuranceSalesService.createApplyContract(request)) {
			return joinApplyContractAndCustomer(model);
		} else {
			return "error";
		}

	}

	public String joinApplyContractAndCustomer(Model model) {
		if (this.insuranceSalesService.joinApplyContractAndCustomer()) {
			model.addAttribute("JudgeResult", "회원가입이 완료되었습니다.");
			return "menu//showResult";
		} else {
			return "error";
		}

	}
	
	@RequestMapping(value = "rejoin", method = RequestMethod.GET)
	public String rejoin(HttpServletRequest request, Model model) {
		if(this.insuranceSalesService.searchFailContract(request) != null) {
			List<Contract> failContractList = this.insuranceSalesService.searchFailContract(request);
			model.addAttribute("failContractList", failContractList);
			return "salesTeam//insuranceSales//rejoinSelection";
		}else {
			//검색된 계약실패내역이 없을 때
		}
		
		return "error";
	}
	
	@RequestMapping(value = "rejoinSelection", method = RequestMethod.GET)
	public String rejoinSelection(HttpServletRequest request, Model model) {
		Contract selectFailContract = this.insuranceSalesService.selectFailContract(request);
		model.addAttribute("customerName", selectFailContract.getCustomerName());
		model.addAttribute("insuranceName", selectFailContract.getInsuranceName());
		model.addAttribute("paymentCycle", selectFailContract.getPaymentCycle());
		model.addAttribute("insuranceFee", selectFailContract.getInsuranceFee());
		model.addAttribute("securityFee", selectFailContract.getSecurityFee());
		model.addAttribute("period", selectFailContract.getPeriod());
		model.addAttribute("reason", selectFailContract.getReason());
		
		return "salesTeam//insuranceSales//rejoinEdit";
	}
	
	@RequestMapping(value = "rejoinEdit", method = RequestMethod.GET)
	public String rejoinEdit(HttpServletRequest request, Model model) {
		if(this.insuranceSalesService.rejoin(request)) {
			model.addAttribute("JudgeResult", "재가입이 완료되었습니다.");
			return "menu//showResult";
		}
		//변경된 사항이 없을 때
		return "error";
	}

	@RequestMapping(value = "finishSales", method = RequestMethod.GET)
	public String finishSales(HttpServletRequest request, Model model) {
		return "menu";
	}
}
