package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.dto.damageAssessment.compansate.ContractAccidentDto;

public interface ContractAccidentDao {

	public void insertContractProvision(ContractAccidentDto contractAccidentDto);

	public void commit();


}
