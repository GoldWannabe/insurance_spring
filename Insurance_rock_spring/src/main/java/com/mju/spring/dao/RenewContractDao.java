package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewContractManagementDto;

public interface RenewContractDao {
	public List<RenewContractDto> retriveRenewContractList();

	public void insertApplyRenew(RenewContractManagementDto renewContractManagementDto);

	public void commit();

	public int deleteRenew(String contractID);
}
