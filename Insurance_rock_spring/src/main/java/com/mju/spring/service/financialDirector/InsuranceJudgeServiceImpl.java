package com.mju.spring.service.financialDirector;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.RegisterInsuranceDao;
import com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto;
import com.mju.spring.entity.Insurance;
import com.mju.spring.entity.Insurance.EInsurance;

@Service
public class InsuranceJudgeServiceImpl implements InsuranceJudgeService{

	@Autowired RegisterInsuranceDao registerInsuranceDao;
	private Insurance insurance;
	
	@Override
	public List<RegisterInsuranceDto> getRegisterInsurance() {
		return this.registerInsuranceDao.retriveRegisterInsuranceList();
	}

	@Override
	public Insurance selectJudgeInsurance(HttpServletRequest request) {
		
		if(request.getParameter("type").equals("general")) {
			//이어서 요율도 따로 받아와야함
			return this.insurance = this.registerInsuranceDao.retriveGeneralName(request.getParameter("name"));
		} else if(request.getParameter("type").equals("house")) {
			return this.insurance = this.registerInsuranceDao.retriveHouseName(request.getParameter("name"));
		} else {
			return null;
		}

	}

}
