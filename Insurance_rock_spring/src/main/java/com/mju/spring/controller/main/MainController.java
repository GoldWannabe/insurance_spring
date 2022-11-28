package com.mju.spring.controller.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
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
			return "test";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "contractTeamMenu", method = RequestMethod.GET)
	public String financialDirectormControl(HttpServletRequest request) {

		if (request.getParameter("menu").equals("design")) {
			return "contractTeam//insuranceDesign//inputTypeAndTerm";
		} else if (request.getParameter("menu").equals("")) {
			return "";
		} else if (request.getParameter("menu").equals("")) {
			return "";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "financialDirectorMenu", method = RequestMethod.GET)
	public String contractTeamControl(HttpServletRequest request) {

		if (request.getParameter("menu").equals("judge")) {
			return "financialDirector//insuranceJudge//startJudge";
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu";
		} else {
			return "error";
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
			return "menu";
		} else {
			return "error";
		}

	}
	
	@RequestMapping(value = "compensateTeamMenu", method = RequestMethod.GET)
	public String compensateTeamControl(HttpServletRequest request) {

		if (request.getParameter("menu").equals("damageAssessment")) {
			return "compensateTeam//damageAssessment//selectAccidentReportMenu";
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}

	}
	@RequestMapping(value = "showResult", method = RequestMethod.GET)
	public String showResult(HttpServletRequest request) {

		
			return "menu//menu";

	}
}
