package com.mju.spring.dao;

import com.mju.spring.entity.Rank;

public interface RankDao {
	
	public int create(Rank rank);
	
	public void commit();

}
