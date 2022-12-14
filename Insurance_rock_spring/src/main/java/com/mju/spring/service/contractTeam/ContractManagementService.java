package com.mju.spring.service.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.contractTeam.contractManagement.InsuranceDetailsDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerPopupDto;
import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;

public interface ContractManagementService {

	public List<Contract> contractSearch(HttpServletRequest request);

	public List<Accident> searchAccidentHistory(HttpServletRequest request);

	public boolean applyRenew(HttpServletRequest request);

	public RenewCustomerPopupDto cancelRenew(HttpServletRequest request);

	public InsuranceDetailsDto searchInsuranceDetails(HttpServletRequest request);

	public InsuranceDetailsDto getRenewInfo();

}
