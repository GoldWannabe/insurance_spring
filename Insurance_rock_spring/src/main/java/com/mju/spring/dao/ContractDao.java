package com.mju.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.ContractProvisionDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectContractDto;
import com.mju.spring.dto.damageAssessment.compansate.UpdateContractDto;
import com.mju.spring.entity.Contract;

@Repository
public interface ContractDao {
	public List<Contract> retriveNameAndPhoneNum(SelectContractDto selectContractDto);

	public ContractProvisionDto retriveContract(String contractID);

	public void updateContractProvisionFee(UpdateContractDto updateContractDto);

	public void commit();
}
