package com.mju.spring.dao;

import com.mju.spring.dto.damageAssessment.compansate.ConctractAccidentDto;

public interface ContractAccidentDao {

	public void insertContractProvision(ConctractAccidentDto contractAccidentDto);

	public void commit();

}
