package com.mju.spring.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class PopupController {
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@RequestMapping(value = "realPopup", method = RequestMethod.GET)
	public String design() {
		return "realPopup";
	}

	@RequestMapping(value="popupList", method = RequestMethod.GET)
	public String urlMethod(HttpServletRequest request , HttpServletResponse response, Model model ) throws Exception {		
		// popupList라는 이름을 가진 팝업 생성
		
		
		return "popupList";
	}
	
	@RequestMapping(value = "design4", method = RequestMethod.GET)
	public String design4() {
		return "design4";
	}
	
	@RequestMapping(value = "testPopup", method = RequestMethod.GET)
	public String testPopup() {
		return "testPopup";
	}
}
