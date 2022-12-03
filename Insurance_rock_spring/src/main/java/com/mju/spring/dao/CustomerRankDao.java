package com.mju.spring.dao;

import com.mju.spring.dto.CustomerRankDto;

public interface CustomerRankDao {
	int create(CustomerRankDto customerRankDTO);

	void commit();
}
