package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;

public interface RenewContractDao {
	List<RenewContractDto> retriveRenewContractList();
}
