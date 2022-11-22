package com.mju.spring.Controller.ContractTeam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Service.InsuranceDesignService;
import com.mju.spring.VO.InsuranceVO;

@Controller
public class InsuranceDesignController {

	@Autowired
	InsuranceDesignService insuranceDesignService;

	private InsuranceVO insuranceVO;
	private InsuranceDTO insuranceDTO;

	@RequestMapping(value = "inputTypeAndTerm", method = RequestMethod.GET) // design2에서 장기여부, 보험종류 정보 제출.
	public String longTerm_InsurnaceType(HttpServletRequest request, Model model) {

		// InsuranceDTO insuranceDTO = new InsuranceDTO();
//      if(request.getParameter("longTerm").equals(true)) {
//         insuranceDTO.setLongTerm(true);
//      }else if(request.getParameter("longTerm").equals("단기")) {//term이라는 체크박스 네임 태그에서 장기면 단기면.
//         insuranceDTO.setLongTerm(false);
//      }
//      getParameterValues String[]
//      getParameter String

		this.insuranceDTO = insuranceDesignService.getinsuranceTypeAndTerm(request);

		this.insuranceVO = new InsuranceVO();
		insuranceVO.setLongTerm(insuranceDTO.isLongTerm());
		insuranceVO.setInsuranceType(insuranceDTO.getInsuranceType());

		model.addAttribute("LongTerm", this.insuranceVO.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("InsuranceType", this.insuranceVO.getInsuranceType());

		return "contractTeam//insuranceDesign//inputInsuranceInfo";

	}

	@RequestMapping(value = "insuranceInfo", method = RequestMethod.GET)
	public String insuranceInfo(HttpServletRequest request, Model model) { // design3에서
//		String[] text =  request.getParameterValues("text");//순서대로 배열에 들어간다네? 안되면 각각 하나씩.
		
			this.insuranceDTO = insuranceDesignService.checkName(request); // 중복확인 이때 체크하면서 보험 정볻 다 DTO에 셋해줘. 요율도 다 보내줌
			this.insuranceDTO = insuranceDesignService.getStandardFee();
			// null이면 중복
			if (this.insuranceDTO == null) {
				model.addAttribute("OverlapError", true);// "기존의 보험이름과 중복 됩니다. 다른 이름을 정해주세요."

			} else {
//			보험이름,특약,가입조건,보상조건,설명
				this.insuranceVO.setStandardFee(this.insuranceDTO.getStandardFee());
				this.insuranceVO.setInsuranceName(this.insuranceDTO.getInsuranceName());
				this.insuranceVO.setSpecialContract(this.insuranceDTO.getSpecialContract());
				this.insuranceVO.setApplyCondition(this.insuranceDTO.getApplyCondition());
				this.insuranceVO.setCompensateCondition(this.insuranceDTO.getCompensateCondition());
				this.insuranceVO.setExplanation(this.insuranceDTO.getExplanation());
				this.insuranceVO.setPremiumRate(this.insuranceDTO.getPremiumRate());// 배열로 넣어서 안되면 따로따로 넣어야 하니까 말해줘.to은서

				
				model.addAttribute("InsuranceName", this.insuranceVO.getInsuranceName());
				model.addAttribute("InsuranceType", this.insuranceVO.getInsuranceType());
				model.addAttribute("StandardFee", this.insuranceVO.getStandardFee());
				model.addAttribute("LongTerm", this.insuranceVO.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
				model.addAttribute("SpecialContract", this.insuranceVO.getSpecialContract());
				model.addAttribute("ApplyCondition", this.insuranceVO.getApplyCondition());
				model.addAttribute("CompensateCondition", this.insuranceVO.getCompensateCondition());
				model.addAttribute("Explanation", this.insuranceVO.getExplanation());
				model.addAttribute("PremiumRate", this.insuranceVO.getPremiumRate());
			}
			
			return "contractTeam//insuranceDesign//register";	

	}

	@RequestMapping(value = "rateScrean", method = RequestMethod.GET)
	public String showRateScrean(HttpServletRequest request, Model model) {
		this.insuranceDTO = insuranceDesignService.checkName(request); // 중복확인 이때 체크하면서 보험 정볻 다 DTO에 셋해줘. 요율도 다 보내줌

		this.insuranceVO.setInsuranceName(this.insuranceDTO.getInsuranceName());
		this.insuranceVO.setSpecialContract(this.insuranceDTO.getSpecialContract());
		this.insuranceVO.setApplyCondition(this.insuranceDTO.getApplyCondition());
		this.insuranceVO.setCompensateCondition(this.insuranceDTO.getCompensateCondition());
		this.insuranceVO.setExplanation(this.insuranceDTO.getExplanation());
		return "contractTeam//insuranceDesign//inputRate";
	}

	@RequestMapping(value = "inputRate", method = RequestMethod.GET)
	public String rateByGrade(HttpServletRequest request, Model model) {
		// 요율 배열로.
//		System.out.println(request.getParameter("addRate"));
		this.insuranceDTO = this.insuranceDesignService.checkRate(request);// 직접 적은 요율을 통해 기준보험료 계산.
		// null이면 중복
//		보험이름,특약,가입조건,보상조건,설명
			this.insuranceVO.setStandardFee(this.insuranceDTO.getStandardFee());
			this.insuranceVO.setPremiumRate(this.insuranceDTO.getPremiumRate());// 배열로 넣어서 안되면 따로따로 넣어야 하니까 말해줘.to은서

			
			model.addAttribute("InsuranceName", this.insuranceVO.getInsuranceName());
			model.addAttribute("InsuranceType", this.insuranceVO.getInsuranceType());
			model.addAttribute("StandardFee", this.insuranceVO.getStandardFee());
			model.addAttribute("LongTerm", this.insuranceVO.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
			model.addAttribute("SpecialContract", this.insuranceVO.getSpecialContract());
			model.addAttribute("ApplyCondition", this.insuranceVO.getApplyCondition());
			model.addAttribute("CompensateCondition", this.insuranceVO.getCompensateCondition());
			model.addAttribute("Explanation", this.insuranceVO.getExplanation());
			model.addAttribute("PremiumRate", this.insuranceVO.getPremiumRate());

		return "contractTeam//insuranceDesign//register";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, Model model) {
//		예 누르면 등록
		insuranceDesignService.register();
		if(request.getParameter("register").equals("cancel")){
			insuranceDesignService.saveTempInsurance();
			return "menu";
		}
		return null;
	}

}