package com.mju.spring.controller.contractTeam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.dto.InsuranceDto;
import com.mju.spring.entity.Insurance;
import com.mju.spring.service.InsuranceDesignService;

@Controller
public class InsuranceDesignController {

	@Autowired
	InsuranceDesignService insuranceDesignService;

	private InsuranceDto insuranceDto;
	private Insurance insurance;

	@RequestMapping(value = "inputTypeAndTerm", method = RequestMethod.GET) // design2에서 장기여부, 보험종류 정보 제출.
	public String inputTypeAndTerm(HttpServletRequest request, Model model) {

		// InsuranceDTO insuranceDTO = new InsuranceDTO();
//      if(request.getParameter("longTerm").equals(true)) {
//         insuranceDTO.setLongTerm(true);
//      }else if(request.getParameter("longTerm").equals("단기")) {//term이라는 체크박스 네임 태그에서 장기면 단기면.
//         insuranceDTO.setLongTerm(false);
//      }
//      getParameterValues String[]
//      getParameter String

		this.insuranceDto = insuranceDesignService.getinsuranceTypeAndTerm(request);
//		this.insurance = insuranceDesignService.getinsuranceTypeAndTerm(request);
		System.out.println(request.getParameter(""));
		model.addAttribute("LongTerm", this.insuranceDto.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("InsuranceType", this.insuranceDto.getInsuranceType());

		return "contractTeam//insuranceDesign//inputInsuranceInfo";

	}

	@RequestMapping(value = "inputInsuranceInfo", method = RequestMethod.GET)
	public String inputInsuranceInfo(HttpServletRequest request, Model model) { // design3에서

		// this.insurance = insuranceDesignService.getinsuranceTypeAndTerm(request);
		this.insuranceDto = insuranceDesignService.checkName(request); // 중복확인 이때 체크하면서 보험 정볻 다 DTO에 셋해줘. 요율도 다 보내줌
		this.insuranceDto = insuranceDesignService.getStandardFee();

		if (request.getParameter("check").equals("confirm")) {
//			보험이름,보험 타입, 기준보험료, 장기여부, 특약,가입조건,보상조건,설명, 요율
//				model.addAttribute("InsuranceList", this.insurance);
			model.addAttribute("InsuranceType", this.insuranceDto.getInsuranceType());
			model.addAttribute("InsuranceType", this.insuranceDto.getInsuranceType());
			model.addAttribute("StandardFee", this.insuranceDto.getStandardFee());
			model.addAttribute("LongTerm", this.insuranceDto.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
			model.addAttribute("SpecialContract", this.insuranceDto.getSpecialContract());
			model.addAttribute("ApplyCondition", this.insuranceDto.getApplyCondition());
			model.addAttribute("CompensateCondition", this.insuranceDto.getCompensateCondition());
			model.addAttribute("Explanation", this.insuranceDto.getExplanation());
			model.addAttribute("PremiumRate", this.insuranceDto.getPremiumRate());
			return "contractTeam//insuranceDesign//register";
		} else if (request.getParameter("check").equals("cancel")) {
			return "contractTeam//insuranceDesign//inputRate";
		}

		// null이면 중복
		if (this.insuranceDto == null) {
			model.addAttribute("OverlapError", true);// "기존의 보험이름과 중복 됩니다. 다른 이름을 정해주세요."
		}
		return null;

	}

	@RequestMapping(value = "inputRate", method = RequestMethod.GET)
	public String inputRate(HttpServletRequest request, Model model) {
		// 요율 배열로.

		this.insuranceDto = this.insuranceDesignService.checkRate(request);// 직접 적은 요율을 통해 기준보험료 계산.
		// null이면 중복
//		보험이름,특약,가입조건,보상조건,설명

//		model.addAttribute("InsuranceList", this.insurance);
		model.addAttribute("InsuranceName", this.insuranceDto.getInsuranceName());
		model.addAttribute("InsuranceType", this.insuranceDto.getInsuranceType());
		model.addAttribute("StandardFee", this.insuranceDto.getStandardFee());
		model.addAttribute("LongTerm", this.insuranceDto.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("SpecialContract", this.insuranceDto.getSpecialContract());
		model.addAttribute("ApplyCondition", this.insuranceDto.getApplyCondition());
		model.addAttribute("CompensateCondition", this.insuranceDto.getCompensateCondition());
		model.addAttribute("Explanation", this.insuranceDto.getExplanation());
		model.addAttribute("PremiumRate", this.insuranceDto.getPremiumRate());

		return "contractTeam//insuranceDesign//register";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, Model model) {
//		예 누르면 등록
		insuranceDesignService.register();
		if (request.getParameter("register").equals("cancel")) {
			insuranceDesignService.saveTempInsurance();
			return "menu";
		}
		return null;
	}

}