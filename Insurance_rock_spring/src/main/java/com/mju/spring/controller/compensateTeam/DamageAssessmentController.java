package com.mju.spring.controller.compensateTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;
import com.mju.spring.service.compensateTeam.DamageAssessmentService;

@Controller
public class DamageAssessmentController {

	@Autowired
	DamageAssessmentService damageAssessmentService;

	@RequestMapping(value = "selectAccidentReportMenu", method = RequestMethod.GET)
	public String selectAccidentReport(HttpServletRequest request, Model model) {
		if (request.getParameter("menu").equals("search")) {
			return "compensateTeam//damageAssessment//inputCustomerNameAndDate";
		} else if (request.getParameter("menu").equals("add")) {
			return "compensateTeam//damageAssessment//inputCustomerNameAndNum";
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	// 추가
	// inputNameAndPhoneNum
	// 뷰에서 이름과 폰번호 입력 후 서비스에서 계약들 리턴 / customerID
	@RequestMapping(value = "inputCustomerNameAndNum", method = RequestMethod.GET)
	public String inputNameAndPhoneNum(HttpServletRequest request, Model model) {
		List<Contract> contractList = this.damageAssessmentService.addcheck(request);
		model.addAttribute("ContractList", contractList);

		return "compensateTeam//damageAssessment//selectAddContract";
	}

	// selectAddContract
	// 뷰에서 계약을 선택하고 서비스에서는 해당 엔티티 셋팅
	@RequestMapping(value = "selectAddContract", method = RequestMethod.GET)
	public String selectAddContract(HttpServletRequest request, Model model) {
		this.damageAssessmentService.setSelectContract(request);
		return "compensateTeam//damageAssessment//inputAccidentInfo";
	}

	// inputAccidentInfo
	// 뷰에서 사고날짜 입력 서비스에서 내용을 받아서 사고 엔티티 리턴
	@RequestMapping(value = "inputAccidentInfo", method = RequestMethod.GET)
	public String inputAccidentInfo(HttpServletRequest request, Model model) {
		Accident accident = this.damageAssessmentService.addAccident(request);
		model.addAttribute("Accident", accident);

		return "compensateTeam//damageAssessment//checkAccidentInfo";
	}
	
	@RequestMapping(value = "checkAccidentInfo", method = RequestMethod.GET)
	public String checkAccidentInfo(HttpServletRequest request, Model model) {
		return "menu//menu";
	}

	// 검색
	// inputNameAndDate
	// 뷰에서 이름과 사고 날짜 입력 서비스에서는 해당하는 것들 찾아옴
	// selectAccident
	// 뷰에서 사고들 중 보상할 것 선택하고 서비스에서느 해당하는 것 선택 후 고객 정보와 사고 정보 리턴
	// selectCompensation
	// 뷰에서 보상여부 선택 후 서비스에서 보상
	// 버튼이름 select provision,edit

}
