package com.mju.spring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String teamControl(HttpServletRequest request) {

		String team = request.getParameter("team");

		if (team.equals("contractTeamMenu")) {
			return "contractTeam//contractTeamMenu";
		} else if (team.equals("financialDirector")) {
			return "financialDirector//financialDirectorMenu";
		} else if (team.equals("salesTeam")) {
			return "salesTeam//salesTeamMenu";
		} else if (team.equals("compensateTeam")) {
			return "compensateTeam";
		} else if (team.equals("policyholder")) {
			return "test";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "contractTeamMenu", method = RequestMethod.GET)
	public String financialDirectormControl(HttpServletRequest request) {

		if (request.getParameter("menu").equals("design")) {
			return "contractTeam//insuranceDesign//design";
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
		}  else {
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
}
