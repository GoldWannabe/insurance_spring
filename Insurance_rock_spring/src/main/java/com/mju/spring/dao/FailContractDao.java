package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.FailContractDto;
import com.mju.spring.entity.Contract;

public interface FailContractDao {

	int create(Contract contract);

	List<Contract> retriveFailContract(FailContractDto failContractDTO);
	
	void commit();

	int delete(Contract selectedFailContract);

	

}
