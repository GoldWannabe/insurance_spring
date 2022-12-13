package com.mju.spring.controller.salesTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Insurance;
import com.mju.spring.exception.NonExistAnyChangeException;
import com.mju.spring.exception.NonExistFailContractException;
import com.mju.spring.exception.NonExistInsuranceException;
import com.mju.spring.service.salesTeam.InsuranceSalesService;

@Controller
public class InsuranceSalesController {

	@Autowired
	InsuranceSalesService insuranceSalesService;

	private List<Insurance> insuranceList;
	private Contract selectFailContract;

	@RequestMapping(value = "selectInsuranceType", method = RequestMethod.GET)
	public String selectInsuranceType(HttpServletRequest request, Model model) {
		if(request.getParameter("next").equals("next")) {
			this.insuranceList = this.insuranceSalesService.getInsuranceList(request);
			model.addAttribute("InsuranceList", insuranceList);
			return "salesTeam//insuranceSales//inputInsuranceName";
		}else if(request.getParameter("next").equals("cancel")) {
			//A1. 취소 버튼을 클릭한 경우
			return "menu//menu";
		}
		return "menu//error";
	}

	@RequestMapping(value = "inputInsuranceName", method = RequestMethod.GET)
	public String selectInsurance(HttpServletRequest request, Model model) {
		model.addAttribute("Popup", false);
		if (this.insuranceSalesService.getInsurance(request) != null) {
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

		} else {
			// E1. 조회된 보험이 없는 경우
			throw new NonExistInsuranceException();
		}
	}

	@RequestMapping(value = "joinSelection", method = RequestMethod.GET)
	public String joinSelection(HttpServletRequest request, Model model) {
		// 보험 가입, 재가입 여부 선택
		if (request.getParameter("join").equals("join")) {
			return "salesTeam//insuranceSales//join";
		} else if (request.getParameter("join").equals("rejoin")) {
			// A2. 재신청 버튼을 눌렀을 경우
			return "salesTeam//insuranceSales//rejoin";
		} else if (request.getParameter("join").equals("cancel")) {
			// A1. 취소 버튼을 눌렀을 경우
			return "menu//menu";
		} else {
			return "menu//error";
		}

	}

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join(HttpServletRequest request, Model model) {
		// A2. 기존 고객일 경우
		if (this.insuranceSalesService.searchCustomer(request)) {
			this.insuranceSalesService.createApplyContract(request);
			return joinApplyContract(model);
		} else {
			// 신규 고객일 경우(basic)
			this.insuranceSalesService.createCustomer(request);
			this.insuranceSalesService.createApplyContract(request);
			return joinApplyContractAndCustomer(model);
		}
	}

	private String joinApplyContract(Model model) {
		// A2. continue - 기존 고객일 경우
		if (this.insuranceSalesService.joinApplyContractAndUpdateCustomer()) {
			model.addAttribute("JudgeResult", "보험 가입 신청이 완료되었습니다.");
			return "menu//showResult";
		} else {
			// E3.DB 접근 오류
			return "menu//error";
		}
	}

	public String joinApplyContractAndCustomer(Model model) {
		if (this.insuranceSalesService.joinApplyContractAndCustomer()) {
			model.addAttribute("JudgeResult", "보험 가입 신청이 완료되었습니다.");
			return "menu//showResult";
		} else {
			// E3.DB 접근 오류
			return "menu//error";
		}

	}

	@RequestMapping(value = "rejoin", method = RequestMethod.GET)
	public String rejoin(HttpServletRequest request, Model model) {
		if (!this.insuranceSalesService.searchFailContract(request).isEmpty()) {
			model.addAttribute("Popup", false);
			List<Contract> failContractList = this.insuranceSalesService.searchFailContract(request);
			model.addAttribute("failContractList", failContractList);
			return "salesTeam//insuranceSales//rejoinSelection";
		} else {

			// E1. 해당하는 계약이 없을 때
			throw new NonExistFailContractException();
		}

	}

	@RequestMapping(value = "rejoinSelection", method = RequestMethod.GET)
	public String rejoinSelection(HttpServletRequest request, Model model) {
			this.selectFailContract = this.insuranceSalesService.selectFailContract(request);
			model.addAttribute("customerName", this.selectFailContract.getCustomerName());
			model.addAttribute("customerPhoneNum", this.selectFailContract.getCustomerPhoneNum());
			model.addAttribute("insuranceName", this.selectFailContract.getInsuranceName());
			model.addAttribute("paymentCycle", this.selectFailContract.getPaymentCycle());
			model.addAttribute("insuranceFee", this.selectFailContract.getInsuranceFee());
			model.addAttribute("securityFee", this.selectFailContract.getSecurityFee());
			model.addAttribute("period", this.selectFailContract.getPeriod());
			model.addAttribute("reason", this.selectFailContract.getReason());
			return "salesTeam//insuranceSales//rejoinEdit";
	}

	@RequestMapping(value = "rejoinEdit", method = RequestMethod.GET)
	public String rejoinEdit(HttpServletRequest request, Model model) {
		if(request.getParameter("rejoin").equals("rejoin")) {
			if (this.insuranceSalesService.rejoin(request)) {
				model.addAttribute("Popup", false);
				model.addAttribute("JudgeResult", "재가입이 완료되었습니다.");
				return "menu//showResult";
			}else {
				// E3. 수정 사항이 없을 때
				throw new NonExistAnyChangeException();
			}
		}else if(request.getParameter("rejoin").equals("cancel")) {
//			A1. 취소 버튼을 누른 경우
			return "menu//menu";
		}
		return "menu//error";
		
	}

	@RequestMapping(value = "finishSales", method = RequestMethod.GET)
	public String finishSales(HttpServletRequest request, Model model) {
		return "menu//menu";
	}

	// -------------"보험을 판매한다" Exception-------------

	// E1. 조회된 보험이 없는 경우
	@ExceptionHandler(NonExistInsuranceException.class)
	private ModelAndView nonExistInsuranceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("InsuranceList", this.insuranceList);
		modelAndView.addObject("Popup", true);
		modelAndView.addObject("Message", e.getMessage());
		modelAndView.setViewName("salesTeam//insuranceSales//inputInsuranceName");
		return modelAndView;
	}
	
	// E2. 정보 입력창이 비어있는 경우 - .jsp(View)에서 처리.

	// E3. DB 접근에 실패한 경우
	@ExceptionHandler(PersistenceException.class)
	private ModelAndView persistenceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult",
				"DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		return modelAndView;
	}

	// -------------"보험을 재가입 신청을 받는다" Exception-------------

	// E1. 조회된 반려계약이 없는 경우
	@ExceptionHandler(NonExistFailContractException.class)
	private ModelAndView nonExistFailContractException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("Popup", true);
		modelAndView.addObject("Message", e.getMessage());
		modelAndView.setViewName("salesTeam//insuranceSales//rejoin");
		return modelAndView;
	}
	
	// E2. DB 접근에 실패한 경우 - "보험을 판매한다" E3와 동일함
	
	// E3. 수정 사항이 없는 경우
	@ExceptionHandler(NonExistAnyChangeException.class)
	private ModelAndView nonExistAnyChangeException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customerName", this.selectFailContract.getCustomerName());
		modelAndView.addObject("customerPhoneNum", this.selectFailContract.getCustomerPhoneNum());
		modelAndView.addObject("insuranceName", this.selectFailContract.getInsuranceName());
		modelAndView.addObject("paymentCycle", this.selectFailContract.getPaymentCycle());
		modelAndView.addObject("insuranceFee", this.selectFailContract.getInsuranceFee());
		modelAndView.addObject("securityFee", this.selectFailContract.getSecurityFee());
		modelAndView.addObject("period", this.selectFailContract.getPeriod());
		modelAndView.addObject("reason", this.selectFailContract.getReason());
		modelAndView.addObject("Popup", true);
		modelAndView.addObject("Message", e.getMessage());
		modelAndView.setViewName("salesTeam//insuranceSales//rejoinEdit");
		return modelAndView;
	}

}
