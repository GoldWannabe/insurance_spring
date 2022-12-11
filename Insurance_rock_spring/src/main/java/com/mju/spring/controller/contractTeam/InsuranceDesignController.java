package com.mju.spring.controller.contractTeam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.dto.contractTeam.insuranceDesign.InsuranceTypeAndTermDto;
import com.mju.spring.entity.Insurance;
import com.mju.spring.service.contractTeam.InsuranceDesignService;

@Controller
public class InsuranceDesignController {

	@Autowired
	InsuranceDesignService insuranceDesignService;

	private Insurance insurance;
	private InsuranceTypeAndTermDto insuranceTypeAndTermDto;

	@RequestMapping(value = "inputTypeAndTerm", method = RequestMethod.GET) // design2에서 장기여부, 보험종류 정보 제출.
	public String inputTypeAndTerm(HttpServletRequest request, Model model) {

		this.insuranceTypeAndTermDto = insuranceDesignService.getinsuranceTypeAndTerm(request);
		model.addAttribute("LongTerm", this.insuranceTypeAndTermDto.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("InsuranceType", this.insuranceTypeAndTermDto.getInsuranceType());
		model.addAttribute("PremiumRate", this.insuranceTypeAndTermDto.getPremiumRate());

		return "contractTeam//insuranceDesign//inputInsuranceInfo";

	}

	@RequestMapping(value = "inputInsuranceInfo", method = RequestMethod.GET)
	public String inputInsuranceInfo(HttpServletRequest request, Model model) {

		this.insurance = insuranceDesignService.checkName(request); // 중복확인 이때 체크하면서 보험 정볻 다 DTO에 셋해줘. 요율도 다 보내줌
		this.insurance = insuranceDesignService.getStandardFee();
		// error
		// null이면 중복
		if (this.insurance == null) {
			model.addAttribute("OverlapError", "기존의 보험이름과 중복 됩니다. 다른 이름을 입력해주세요.");
		}
		if (request.getParameter("check").equals("confirm")) {
//			보험이름,보험 타입, 기준보험료, 장기여부, 특약,가입조건,보상조건,설명, 요율
			model.addAttribute("InsuranceName", this.insurance.getInsuranceName());
			model.addAttribute("InsuranceType", this.insurance.getInsuranceType());
			model.addAttribute("StandardFee", this.insurance.getStandardFee());
			model.addAttribute("LongTerm", this.insurance.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
			model.addAttribute("SpecialContract", this.insurance.getSpecialContract());
			model.addAttribute("ApplyCondition", this.insurance.getApplyCondition());
			model.addAttribute("CompensateCondition", this.insurance.getCompensateCondition());
			model.addAttribute("Explanation", this.insurance.getExplanation());
			model.addAttribute("PremiumRate", this.insurance.getPremiumRate());
			return "contractTeam//measureStandardFee//register";
		} else if (request.getParameter("check").equals("cancel")) {
			return "contractTeam//insuranceDesign//inputRate";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "inputRate", method = RequestMethod.GET)
	public String inputRate(HttpServletRequest request, Model model) {
		// 요율 배열로.

		this.insurance = this.insuranceDesignService.checkRate(request);// 직접 적은 요율을 통해 기준보험료 계산.
		// null이면 중복
//		보험이름,특약,가입조건,보상조건,설명

//		model.addAttribute("InsuranceList", this.insurance);
		model.addAttribute("InsuranceName", this.insurance.getInsuranceName());
		model.addAttribute("InsuranceType", this.insurance.getInsuranceType());
		model.addAttribute("StandardFee", this.insurance.getStandardFee());
		model.addAttribute("LongTerm", this.insurance.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("SpecialContract", this.insurance.getSpecialContract());
		model.addAttribute("ApplyCondition", this.insurance.getApplyCondition());
		model.addAttribute("CompensateCondition", this.insurance.getCompensateCondition());
		model.addAttribute("Explanation", this.insurance.getExplanation());
		model.addAttribute("PremiumRate", this.insurance.getPremiumRate());

		return "contractTeam//measureStandardFee//register";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, Model model) {
//		예 누르면 등록
		if (request.getParameter("register").equals("register")) {
			model.addAttribute("JudgeResult", "보험 등록이 완료되었습니다.");
			insuranceDesignService.register();
			return "menu//showResult";
		} else if (request.getParameter("register").equals("cancel")) {
			boolean checkSaveTemp = insuranceDesignService.saveTempInsurance();
			if (checkSaveTemp) {
				model.addAttribute("JudgeResult", "입력된 정보가 임시저장되었습니다.");
				return "menu//showResult";
			} else {
				model.addAttribute("JudgeResult",
						"파일 접근 중 문제가 생겨 보험정보를 불러오지 못했습니다. 잠시후 다시 실행해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-4567)에게 문의 주시기 바랍니다");
				return "menu//showResult";
			}
		} else {
			return "error";
		}
	}

}