package com.mju.spring.dao;

import org.springframework.stereotype.Repository;


@Repository
public interface ContractAccidentDao {

	public void insertContractProvision(String[] contractIDAndAccidentID);

}
