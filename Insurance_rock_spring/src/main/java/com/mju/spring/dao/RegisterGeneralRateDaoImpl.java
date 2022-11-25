package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mju.spring.entity.Insurance;

@Repository
public class RegisterGeneralRateDaoImpl implements RegisterGeneralRateDao {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "RegisterGeneralRateMapper.create";

	@Override
	public int create(Insurance insurance) {
		return sqlSession.insert(Create, insurance);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

}
