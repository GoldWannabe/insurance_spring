package com.mju.spring.dao;

import com.mju.spring.dto.damageAssessment.compansate.ContractAccidentDto;

public interface ContractAccidentDao {

	public void insertContractProvision(ContractAccidentDto contractAccidentDto);

	public void commit();


}
