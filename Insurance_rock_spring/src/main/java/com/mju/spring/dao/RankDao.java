package com.mju.spring.dao;

import com.mju.spring.dto.contractTeam.contractManagement.InsuranceDetailsDto;
import com.mju.spring.entity.Rank;

public interface RankDao {
	
	public int create(Rank rank);
	
	public void commit();

	public Rank retriveRankById(String rankID);

	public int updateRank(Rank rank);

	public int deleteRank(String rankID);


}
