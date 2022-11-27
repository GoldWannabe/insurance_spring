package com.mju.spring.DAO;

import com.mju.spring.Entity.Contract;

public interface ContractDAO {

	int create(Contract contract);

	void commit();

}
