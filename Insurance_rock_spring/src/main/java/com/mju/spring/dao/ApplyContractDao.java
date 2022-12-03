package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;

public interface ApplyContractDao {

	List<ApplyContractDto> retriveApplyContractList();

}
