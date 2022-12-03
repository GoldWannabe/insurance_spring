package com.mju.spring.dao;

import com.mju.spring.entity.Rank;

public interface RankDao {
	
	int create(Rank rank);
	
	void commit();


}
