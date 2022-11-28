package com.mju.spring.dao;

import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.ConctractAccidentDto;


@Repository
public interface ContractAccidentDao {

	public void insertContractProvision(ConctractAccidentDto contractAccidentDto);

}
