package com.mju.spring.controller.main;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.entity.Insurance;
import com.mju.spring.service.contractTeam.InsuranceDesignService;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	InsuranceDesignService insuranceDesignService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "menu//menu";
	}

	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String teamControl(HttpServletRequest request) {

		String team = request.getParameter("team");

		if (team.equals("contractTeamMenu")) {
			return "menu//contractTeamMenu";
		} else if (team.equals("financialDirector")) {
			return "menu//financialDirectorMenu";
		} else if (team.equals("salesTeam")) {
			return "menu//salesTeamMenu";
		} else if (team.equals("compensateTeam")) {
			return "menu//compensateTeamMenu";
		} else if (team.equals("policyholder")) {
			return "menu//policyholderMenu";
		} else {
			return "menu//error";
		}

	}

	@RequestMapping(value = "contractTeamMenu", method = RequestMethod.GET)
	public String financialDirectormControl(HttpServletRequest request, Model model) {

		if (request.getParameter("menu").equals("design")) {
//			임시저장된 내용이 있다면. 가져와서 바로 return register로.
			Insurance insurance = insuranceDesignService.getTempInsurance(request);
			if (insurance != null) {
				model.addAttribute("TempInsurance", "---임시저장된 내용--");
				model.addAttribute("InsuranceName", insurance.getInsuranceName());
				model.addAttribute("InsuranceType", insurance.getInsuranceType());
				model.addAttribute("StandardFee", insurance.getStandardFee());
				model.addAttribute("LongTerm", insurance.isLongTerm()); // 마지막에 보여주는 화면에 대한 내용 보내주기.
				model.addAttribute("SpecialContract", insurance.getSpecialContract());
				model.addAttribute("ApplyCondition", insurance.getApplyCondition());
				model.addAttribute("CompensateCondition", insurance.getCompensateCondition());
				model.addAttribute("Explanation", insurance.getExplanation());
				model.addAttribute("PremiumRate", insurance.getPremiumRate());
				return "contractTeam//measureStandardFee//register";
			}
			else {
				return "contractTeam//insuranceDesign//inputTypeAndTerm";
			}
		} else if (request.getParameter("menu").equals("underwrite")) {
			return "contractTeam//underwriting//selectUnderwrite";
		} else if (request.getParameter("menu").equals("contractManagement")) {
			return "contractTeam//contractManagement//selectContractSearchAndCancel";
		} else {
			return "menu//error";
		}

	}

	@RequestMapping(value = "financialDirectorMenu", method = RequestMethod.GET)
	public String contractTeamControl(HttpServletRequest request) {

		if (request.getParameter("menu").equals("judge")) {
			return "financialDirector//insuranceJudge//startJudge";
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "menu//error";
		}

	}

	@RequestMapping(value = "salesTeamMenu", method = RequestMethod.GET)
	public String salesTeamControl(HttpServletRequest request) {

		if (request.getParameter("menu").equals("insuranceSales")) {
			return "salesTeam//insuranceSales//selectInsuranceType";
		} else if (request.getParameter("menu").equals("customerManagement")) {
			return "";
		} else if (request.getParameter("menu").equals("channelManagement")) {
			return "";
		} else if (request.getParameter("menu").equals("cancellation")) {
			return "menu//menu";
		} else {
			return "menu//error";
		}

	}

	@RequestMapping(value = "compensateTeamMenu", method = RequestMethod.GET)
	public String compensateTeamControl(HttpServletRequest request) {

		if (request.getParameter("menu").equals("damageAssessment")) {
			return "compensateTeam//damageAssessment//selectAccidentReportMenu";
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "menu//error";
		}

	}

	@RequestMapping(value = "policyholderMenu", method = RequestMethod.GET)
	public String policyholderControl(HttpServletRequest request, Model model) {

		if (request.getParameter("menu").equals("payInsuranceFee")) {
			
			model.addAttribute("Today", LocalDate.now());
			return "policyholder//checkInsuranceFee//inputPolicyholderInfo";
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "menu//error";
		}

	}

	@RequestMapping(value = "showResult", method = RequestMethod.GET)
	public String showResult(HttpServletRequest request) {

		return "menu//menu";

	}

	@RequestMapping(value = "errorMenu", method = RequestMethod.GET)
	public String showerrorMenu(HttpServletRequest request) {

		return "menu//menu";

	}
}
