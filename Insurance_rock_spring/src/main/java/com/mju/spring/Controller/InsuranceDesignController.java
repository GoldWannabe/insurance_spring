package com.mju.spring.Controller;
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
	public void insuranceType(EInsurance insuranceType , boolean longTerm, Model model) {
		//중간 작업 불러오기. 
		InsuranceDTO insuranceDTO = new InsuranceDTO();
		insuranceDTO.setLongTerm(longTerm);
		insuranceDTO = insuranceDesignService.getinsuranceType(insuranceType);
		//String으로 받음.
		
		insuranceVO.setInsuranceType(null); // insuranceDTO.getinsuranceType();
		model.addAttribute("longTerm", insuranceVO.isLongTerm());
		
//		model.addAllAttributes("longTerm", insuranceVO.isLongTerm());

	}
	@RequestMapping(value = "insuranceType", method = RequestMethod.GET)
	public void insuranceDesign(String insuranceType , boolean longTerm) {
		
		
	}
	
	
}
