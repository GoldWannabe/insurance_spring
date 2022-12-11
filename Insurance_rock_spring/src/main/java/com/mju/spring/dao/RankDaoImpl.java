package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Rank;


@Repository
public class RankDaoImpl implements RankDao {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "RankMapper.create";
	private static final String SelectRankById = "RankMapper.selectRankById";
	private static final String UpdateRank = "RankMapper.updateRank";
	private static final String DeleteRank = "RankMapper.deleteRank";

	@Override
	public int create(Rank rank) {
		return sqlSession.insert(Create, rank);
	}

	@Override
	public void commit() {
		sqlSession.commit();
		
	}

	@Override
	public Rank retriveRankById(String rankID) {
		return sqlSession.selectOne(SelectRankById, rankID);
	}

	@Override
	public int updateRank(Rank rank) {
		return sqlSession.update(UpdateRank, rank);
	}

	@Override
	public int deleteRank(String rankID) {
		return sqlSession.delete(DeleteRank, rankID);
	}


}
