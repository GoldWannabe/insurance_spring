package com.mju.spring.DAO;

import com.mju.spring.DTO.CustomerRankDTO;

public interface CustomerRankDAO {
	int create(CustomerRankDTO customerRankDTO);

	void commit();
}
