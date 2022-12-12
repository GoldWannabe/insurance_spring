package com.mju.spring.controller.contractTeam;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mju.spring.dto.contractTeam.insuranceDesign.InsuranceTypeAndTermDto;
import com.mju.spring.entity.Insurance;
import com.mju.spring.exception.OverlapInsuranceNameException;
import com.mju.spring.exception.TextFileAcceptException;
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
		// null이면 중복
		if (this.insurance == null) {
			throw new OverlapInsuranceNameException();
		}else
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
				return "menu//error";
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
				throw new TextFileAcceptException();
			}
		} else {
			return "menu//error";
		}
	}
	///////////////(공통)보험을 설계한다. 기준보험료를 특정한다////////////
//	E2. 텍스트 파일 접근에 실패한 경우  E4. 텍스트 파일 접근에 실패한 경우
	@ExceptionHandler(TextFileAcceptException.class)
	private ModelAndView textFileAcceptException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;
	}
	////////////기준보험료를 측정한다.////////////
//	E1.보험 DB의 이름이 중복될 경우  E2.심사 대기 중인 보험과 이름이 중복될 경우
	@ExceptionHandler(OverlapInsuranceNameException.class)
	private ModelAndView overlapInsurnaceNameException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contractTeam//insuranceDesign//inputInsuranceInfo");
		modelAndView.addObject("OverlapError", e.getMessage());
		return modelAndView;
	}
//	E3. DB 접근에 실패한 경우 
	@ExceptionHandler(PersistenceException.class)
	private ModelAndView persistenceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;
	}

}