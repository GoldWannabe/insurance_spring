package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.Entity.Insurance;

@Repository
public class RegisterHouseRateDaoImpl implements RegisterHouseRateDao {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "RegisterHouseRateMapper.create";

	@Override
	public int create(Insurance insurance) {
		return sqlSession.insert(Create, insurance);
	}

}
