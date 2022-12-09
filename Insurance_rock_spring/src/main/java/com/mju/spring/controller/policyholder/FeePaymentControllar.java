package com.mju.spring.controller.policyholder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.service.policyholder.FeePaymentService;

@Controller
public class FeePaymentControllar {

	@Autowired FeePaymentService feePaymentService;

	
	@RequestMapping(value = "inputPolicyholderInfo", method = RequestMethod.GET)
	public String selectUnderwrite(HttpServletRequest request, Model model) {
		if (request.getParameter("selectPayment").equals("getPayment")) {
			return getPaymentList(request, model);
		} else if (request.getParameter("selectPayment").equals("cancel")) {
			return "main//main";
		} else {
			return "error";
		}
	}


	private String getPaymentList(HttpServletRequest request, Model model) {
		List<PaymentDto> paymentList = this.feePaymentService.getPaymentList(request);
		if(paymentList.size() > 0) {
			model.addAttribute("PaymetnList", paymentList);//보험 이름, 분납/일시불 여부, 보험료, 납부 한 금액,미납액, 보험ID, 고객 ID, 계약ID
			return "policyholder//checkInsuranceFee//selectPayment";
		} else {
			//유즈케이스 E1 return이 아닌 throw를 해야함
			return "error";
		}
	}
}
