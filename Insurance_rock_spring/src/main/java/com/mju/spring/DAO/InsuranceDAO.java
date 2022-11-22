package com.mju.spring.DAO;

import java.util.List;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Entity.Insurance;

public interface InsuranceDAO {

	Insurance retriveName(String insuranceName);

	//List<Insurance> retriveInsuranceList(String insuranceType);

	Insurance retriveGeneralName(String insuranceName);
	Insurance retriveHouseName(String insuranceName);
	
	List<Insurance> retriveGeneralInsuranceList(String insuranceType);
	
	List<Insurance> retriveHouseInsuranceList(String insuranceType);



}
