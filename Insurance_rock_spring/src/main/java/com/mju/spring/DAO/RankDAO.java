package com.mju.spring.DAO;

import com.mju.spring.Entity.Rank;

public interface RankDAO {
	
	int create(Rank rank);
	
	void commit();

}
