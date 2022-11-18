package com.mju.spring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String teamControl(HttpServletRequest request) {
		if (request.getParameter("team").equals("contractTeam")) {
			return "contractTeam";
		} else if (request.getParameter("team").equals("financialDirector")) {
			return "financialDirector";
		} else if (request.getParameter("team").equals("salesTeam")) {
			return "salesTeam";
		} else if (request.getParameter("team").equals("policyholder")) {
			return "policyholder";
		} else if (request.getParameter("team").equals("compensateTeam")) {
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
}
