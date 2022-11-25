package com.mju.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto;
import com.mju.spring.entity.Insurance;

@Repository
public interface RegisterInsuranceDao {

	public String retriveName(String insuranceName);

	public int create(Insurance insurance);

	public void commit();

	public List<RegisterInsuranceDto> retriveRegisterInsuranceList();

	public Insurance retriveGeneralName(String insuranceName);

	public Insurance retriveHouseName(String insuranceName);

}
