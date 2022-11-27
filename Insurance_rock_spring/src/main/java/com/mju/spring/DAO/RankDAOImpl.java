package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.Entity.Rank;

@Repository
public class RankDAOImpl implements RankDAO {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "RankMapper.create";

	@Override
	public int create(Rank rank) {
		return sqlSession.insert(Create, rank);
	}

	@Override
	public void commit() {
		sqlSession.commit();
		
	}

}
