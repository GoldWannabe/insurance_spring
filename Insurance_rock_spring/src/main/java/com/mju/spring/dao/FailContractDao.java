package com.mju.spring.dao;

import com.mju.spring.entity.Contract;

public interface FailContractDao {

	int create(Contract contract);

	void commit();

}
