package com.mju.spring.controller.policyholder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.dto.policyholder.feePayment.DuePaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;
import com.mju.spring.service.policyholder.FeePaymentService;

@Controller
public class FeePaymentControllar {

	@Autowired
	FeePaymentService feePaymentService;

	@RequestMapping(value = "inputPolicyholderInfo", method = RequestMethod.GET)
	public String selectUnderwrite(HttpServletRequest request, Model model) {
		if (request.getParameter("selectPayment").equals("getDuePayment")) {
			return getDuePaymentList(request, model);
		} else if (request.getParameter("selectPayment").equals("cancel")) {
			return "main//main";
		} else {
			return "error";
		}
	}

	private String getDuePaymentList(HttpServletRequest request, Model model) {
		List<DuePaymentDto> duePaymentList = this.feePaymentService.getDuePaymentList(request);
		if (duePaymentList.size() > 0) {
			model.addAttribute("DuePaymentList", duePaymentList);
			return "policyholder//checkInsuranceFee//selectDuePayment";
		} else {
			// 유즈케이스 E1 return이 아닌 throw를 해야함
			return "error";
		}
	}

	@RequestMapping(value = "selectDuePayment", method = RequestMethod.GET)
	public String selectPayment(HttpServletRequest request, Model model) {
		String select[] = request.getParameter("selectPayOrRecord").split(" ");
		if (select[0].equals("payInsuranceFee")) {
			return selectPayment(select[1]);
		} else if (select[0].equals("showPaymentRecord")) {
			return getPaymentRecord(model);
		} else if (select[0].equals("showProvisionRecord")) {
			return getProvisionRecord(model);
		} else if (select[0].equals("cancel")) {
			return "main//main";
		} else {
			return "error";
		}
	}

	private String selectPayment(String num) {
		if(this.feePaymentService.selectDuePayment(Integer.parseInt(num))) {
		return null;
		} else {
			return "error";
		}
	}

	private String getPaymentRecord(Model model) {
		List<PaymentDto> paymentList = this.feePaymentService.getPaymentRecord();
		if(paymentList.size() > 0) {
			model.addAttribute("PaymentList", paymentList);
			return "policyholder//checkInsuranceFee//showPaymentRecord";
			} else { //기록이 없는 에러 던지기
				return "error";
			}
	}

	private String getProvisionRecord(Model model) {
		List<ProvisionDto> provisionList = this.feePaymentService.getProvisionRecord();
		if(provisionList.size() > 0) {
			model.addAttribute("ProvisionList",provisionList);
			return "policyholder//checkInsuranceFee//showProvisionRecord";
			} else {//기록이 없는 에러 던지기
				return "error";
			}
	}
	
	@RequestMapping(value = "showPaymentRecord", method = RequestMethod.GET)
	public String showPaymentRecord(HttpServletRequest request, Model model) {
		if (request.getParameter("selectCheck").equals("check")) {
			model.addAttribute("JudgeResult", "납부 기록 확인이 완료되었습니다.");
			return "menu//showResult";
		}  else {
			return "error";
		}
	}
	
	@RequestMapping(value = "showProvisionRecord", method = RequestMethod.GET)
	public String showProvisionRecord(HttpServletRequest request, Model model) {
		if (request.getParameter("selectCheck").equals("check")) {
			model.addAttribute("JudgeResult", "지급 내역 확인이 완료되었습니다.");
			return "menu//showResult";
		}  else {
			return "error";
		}
	}
}
