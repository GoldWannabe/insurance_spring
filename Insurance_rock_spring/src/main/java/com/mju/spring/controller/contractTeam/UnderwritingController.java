package com.mju.spring.controller.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.entity.Contract;
import com.mju.spring.service.contractTeam.UnderwritingService;

@Controller
public class UnderwritingController {

	@Autowired
	UnderwritingService underwritingService;
//selectUnderwrite 
	// 신규인지 갱신인지 선택(취소)
//selectApply
	// 지원한 계약들 중 선택
	// 보험이름 가입자명 연락처 담보액 보험료 가입기간(달)
	// 검증, 취소

//verifyInsurance
	// if (!getInsurance() || !getCustomer())
	// 해당하는 보험, 고객 가져옴
	// verifyPeriod 장기계약 가능한 보험인지
	// verifyPremium
	// getMaterialRank
	// verifyInsuranceFee 최종 요율 계산

//selectRenew	
	@RequestMapping(value = "selectUnderwrite", method = RequestMethod.GET)
	public String selectUnderwrite(HttpServletRequest request, Model model) {
		if (request.getParameter("underwriteMenu").equals("apply")) {
			return getApply();
		} else if (request.getParameter("underwriteMenu").equals("renew")) {
			return getRenew();
		} else if (request.getParameter("underwriteMenu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}



	private String getApply() {
		List<Contract> contractList = this.underwritingService.getApply();
		return "contractTeam//underwriting//selectApply";
	}

	private String getRenew() {
		List<Contract> contractList = this.underwritingService.getRenew();
		return "contractTeam//underwriting//selectRenew";
	}
}
