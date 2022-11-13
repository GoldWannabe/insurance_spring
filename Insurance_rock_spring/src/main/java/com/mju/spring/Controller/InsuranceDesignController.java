package com.mju.spring.Controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.DTO.InsuranceDTO.EInsurance;
import com.mju.spring.Service.InsuranceDesignService;
import com.mju.spring.VO.InsuranceVO;

@Controller
public class InsuranceDesignController {
	
	@Autowired
	InsuranceDesignService insuranceDesignService;
	InsuranceVO insuranceVO;
	
	@RequestMapping(value = "insuranceType", method = RequestMethod.GET)
	public void insuranceType(HttpServletRequest request) {
		//중간 작업 불러오기. 
		InsuranceDTO insuranceDTO = new InsuranceDTO();
		if(request.getParameter("term").equals("장기")) {
			insuranceDTO.setLongTerm(true);
		}else if(request.getParameter("term").equals("단기")) {//term이라는 체크박스 네임 태그에서 장기면 단기면.
			insuranceDTO.setLongTerm(false);
		}
//		getParameterValues String[]
//		getParameter String
		
		
		//enum set은 서비스에서 하는것. 서비스에서 판별후 DTO에 셋해주는것까지.
//		insuranceDTO = insuranceDesignService.getTempInsurance(request.getParameter("type")); 
		
		insuranceVO.setLongTerm(insuranceDTO.isLongTerm());
//		insuranceVO.setInsuranceType(insuranceDTO.getInsuranceType());
//		1.TO STRING 받기.
		

	}
	@RequestMapping(value = "text", method = RequestMethod.GET)
	public void insuranceDesign(HttpServletRequest request, Model model) {
		String[] text =  request.getParameterValues("text");//순서대로 배열에 들어간다네? 안되면 각각 하나씩.
		
		
//		model.addAttribute("text", insuranceVO.isLongTerm()); 마지막에 보여주는 화면에 대한 내용 보내주기.
	}
	
	
}
