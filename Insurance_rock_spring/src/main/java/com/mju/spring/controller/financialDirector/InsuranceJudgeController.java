package com.mju.spring.controller.financialDirector;

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

import com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto;
import com.mju.spring.entity.Insurance;
import com.mju.spring.exception.NotFindJudgedInsuranceException;
import com.mju.spring.service.financialDirector.InsuranceJudgeService;

@Controller
public class InsuranceJudgeController {
        
	@Autowired
	InsuranceJudgeService insuranceJudgeService;

	@RequestMapping(value = "startJudge", method = RequestMethod.GET)
	public String startJudge(HttpServletRequest request, Model model) {
		// 모든 보험 가져와서 리턴 
		if (request.getParameter("menu").equals("start")) {
			List<RegisterInsuranceDto> registerInsuranceDaoList = this.insuranceJudgeService.getRegisterInsurance();
			
			if(registerInsuranceDaoList.size() > 0) {
			model.addAttribute("RegisterInsuranceList", registerInsuranceDaoList);
			return "financialDirector//insuranceJudge//selectJudgeInsurance";
			} else {
				throw new NotFindJudgedInsuranceException();
			}
			
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "menu//error";
		}
	}

	@RequestMapping(value = "selectJudgeInsurance", method = RequestMethod.GET)
	public String selectJudgeInsurance(HttpServletRequest request, Model model) {
		// 선택한 보험 가져와서 리턴
		Insurance insurance = this.insuranceJudgeService.selectJudgeInsurance(request);
		model.addAttribute("Insurance", insurance); 
		return "financialDirector//insuranceJudge//selectPermitInsurance";
	}

	@RequestMapping(value = "selectPermitInsurance", method = RequestMethod.GET)
	public String selectPermitInsurance(HttpServletRequest request, Model model) {
		if (request.getParameter("selectPerit").equals("permit")) {
			return permit(model);

		} else if (request.getParameter("selectPerit").equals("notPermit")) {
			return notPermit(model);
		} else if (request.getParameter("selectPerit").equals("defer")) {

			return defer(model);
		} else {
			return "menu//error";
		}
	}

	private String permit(Model model) {
		if (this.insuranceJudgeService.permit()) {
			model.addAttribute("JudgeResult", "보험이 승인되었습니다.");
			return "menu//showResult";
		} else {
			return "menu//error";
		}
	}

	private String notPermit(Model model) {
		if (this.insuranceJudgeService.notPermit()) {
			model.addAttribute("JudgeResult", "해당 보험이 비승인되었습니다.");
			return "menu//showResult";
		} else {
			return "menu//error";
		}
	}

	private String defer(Model model) {
		model.addAttribute("JudgeResult", "보류되었습니다.");
		return "menu//showResult";

	}

	@ExceptionHandler(PersistenceException.class)
	private ModelAndView handlerPersistenceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", "DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		return modelAndView;

	}
	
	@ExceptionHandler(NotFindJudgedInsuranceException.class)
	private ModelAndView handlerNotFindJudgedInsuranceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

	}
}
