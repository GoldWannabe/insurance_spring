package com.mju.spring.service.financialDirector;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto;
import com.mju.spring.entity.Insurance;

public interface InsuranceJudgeService {

	public List<RegisterInsuranceDto> getRegisterInsurance();

	public Insurance selectJudgeInsurance(HttpServletRequest request);

}
