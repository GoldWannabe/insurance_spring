package com.mju.spring.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.DAO.InsuranceDAO;
import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Entity.GeneralInsurance;
import com.mju.spring.Entity.HouseInsurance;
import com.mju.spring.Entity.Insurance;

@Service
public class InsuranceSalesServiceImpl implements InsuranceSalesService {
  
	private Insurance insurance;
	private InsuranceDTO insuranceDTO;

	@Autowired
	InsuranceDAO insuranceDAO;

	@Override
	public List<InsuranceDTO> getInsuranceList(HttpServletRequest request) {
		List<Insurance> insuranceList = null;
		if (request.getParameter("insuranceType").equals(Insurance.EInsurance.general.toString())) {
			this.insurance = new GeneralInsurance();
			insuranceList = this.insuranceDAO.retriveGeneralInsuranceList(request.getParameter("insuranceType"));

		} else if (request.getParameter("insuranceType").equals(Insurance.EInsurance.house.toString())) {
			this.insurance = new HouseInsurance();
			insuranceList = this.insuranceDAO.retriveHouseInsuranceList(request.getParameter("insuranceType"));
		}
		
		
		List<InsuranceDTO> insuranceDTOList = new ArrayList<InsuranceDTO>();
		
		for(Insurance insurance : insuranceList) {
			InsuranceDTO insuranceDTO = new InsuranceDTO();	
			insuranceDTO.setInsuranceName(insurance.getInsuranceName());
			insuranceDTO.setInsuranceType(insurance.getInsuranceType().toString());
			insuranceDTOList.add(insuranceDTO);
		}
		
		return insuranceDTOList;
	}

}
