package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.contractTeam.contractManagement.SelectContractManagementDto;
import com.mju.spring.dto.damageAssessment.compansate.ContractProvisionDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectContractDto;
import com.mju.spring.dto.damageAssessment.compansate.UpdateContractDto;
import com.mju.spring.dto.policyholder.feePayment.ContractAccountDto;
import com.mju.spring.dto.policyholder.feePayment.DuePaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.entity.Contract;

public interface ContractDao {
	public List<Contract> retriveNameAndPhoneNum(SelectContractDto selectContractDto);

	public ContractProvisionDto retriveContract(String contractID);

	public void updateContractProvisionFee(UpdateContractDto updateContractDto);

	public void commit();

	public int create(Contract contract);

	public List<Contract> retriveContractManagement(SelectContractManagementDto selectContractManagementDto);

	public void deleteContractManagement(String contractID);

	public Contract retriveContractById(String contractID);

	public int updateRenew(Contract contract);

	public List<DuePaymentDto> retrivePayment(PolicyholderDto policyholderDto);

	public int updateUnpaidFee(ContractAccountDto contractAccountDto);
}
