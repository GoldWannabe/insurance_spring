package com.mju.spring.service.compensateTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Provision;

public interface DamageAssessmentService {
	public List<Contract> addcheck(HttpServletRequest request);
	public Accident addAccident(HttpServletRequest request);
	public List<Accident> searchAccident(HttpServletRequest request);
	public Accident selectAccident(HttpServletRequest request);
	public Provision payCompensation();
	public void setSelectContract(HttpServletRequest request);
	public Accident getSelectAccident(HttpServletRequest request);
	public boolean modify(HttpServletRequest request);
	
}
