package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.entity.Insurance;

public interface InsuranceDao {

	public String retriveName(String insuranceName);

	//List<Insurance> retriveInsuranceList(String insuranceType);

	Insurance retriveGeneralName(String insuranceName);
	Insurance retriveHouseName(String insuranceName);
	
	List<Insurance> retriveGeneralInsuranceList(String insuranceType);
	
	List<Insurance> retriveHouseInsuranceList(String insuranceType);

	public int create(Insurance insurance);

	public void commit();



}
