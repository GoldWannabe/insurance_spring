package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.contractTeam.contractManagement.SelectContractManagementDto;
import com.mju.spring.dto.damageAssessment.compansate.ContractProvisionDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectContractDto;
import com.mju.spring.dto.damageAssessment.compansate.UpdateContractDto;
import com.mju.spring.entity.Contract;

public interface ContractDao {
	public List<Contract> retriveNameAndPhoneNum(SelectContractDto selectContractDto);

	public ContractProvisionDto retriveContract(String contractID);

	public void updateContractProvisionFee(UpdateContractDto updateContractDto);

	public void commit();

	public int create(Contract applyContract);

	public List<Contract> retriveContractManagement(SelectContractManagementDto selectContractManagementDto);
}
