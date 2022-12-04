package com.mju.spring.service.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.entity.Contract;

public interface ContractManagementService {

	public List<Contract> contractSearch(HttpServletRequest request);

	public List<ContractManagementAccidentDto> searchAccidentHistory(HttpServletRequest request);

	public boolean applyRenew(HttpServletRequest request);

}
