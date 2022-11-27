package com.mju.spring.service.compensateTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;

public interface DamageAssessmentService {
	public List<Contract> addcheck(HttpServletRequest request);
	public Accident addAccident(HttpServletRequest request);
}
