package com.mju.spring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String teamControl(HttpServletRequest request) {

	String team = request.getParameter("team");
		
		if (team.equals("contractTeam")) {
			return "contractTeam";
		} else if (team.equals("financialDirector")) {
			return "financialDirector";
		} else if (team.equals("salesTeam")) {
			return "salesTeam//salesTeam";
		} else if (team.equals("policyholder")) {
			return "policyholder";
		} else if (team.equals("compensateTeam")) {
			return "compensateTeam";
		} else {
			return "error";
		}
		
	}

	@RequestMapping(value = "contractTeam", method = RequestMethod.GET)
	public String contractTeamControl(HttpServletRequest request) {
		
		if (request.getParameter("menu").equals("design")) {
			return "design";
		} else if (request.getParameter("menu").equals("")) {
			return "";
		} else if (request.getParameter("menu").equals("")) {
			return "";
		} else {
			return "error";
		}

	}
	
	@RequestMapping(value = "salesTeam", method = RequestMethod.GET)
	public String salesTeamControl(HttpServletRequest request) {
		
		if (request.getParameter("menu").equals("insuranceSales")) {
			return "salesTeam//selectInsuranceType";
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
