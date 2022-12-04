package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Rank;


@Repository
public class RankDaoImpl implements RankDao {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "RankMapper.create";
	private static final String DeleteRankRenew = "RankMapper.deleteRankRenew";
	private static final String SelectRankById = "RankMapper.selectRankById";

	@Override
	public int create(Rank rank) {
		return sqlSession.insert(Create, rank);
	}

	@Override
	public void commit() {
		sqlSession.commit();
		
	}

	@Override
	public void deleteRank(String rankID) {
		sqlSession.delete(DeleteRankRenew, rankID);
		
	}

	@Override
	public Rank retriveRankById(String rankID) {
		return sqlSession.selectOne(SelectRankById, rankID);
	}

}
