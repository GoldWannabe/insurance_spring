package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.entity.Contract;

public interface ApplyContractDao {

	List<ApplyContractDto> retriveApplyContractList();

	void commit();

	int create(Contract applyContract);

	int delete(String contractID);

}
