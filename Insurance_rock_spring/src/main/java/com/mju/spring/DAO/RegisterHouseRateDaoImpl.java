package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;

import com.mju.spring.Entity.Insurance;
import com.mju.spring.Mybatis.MyBatisConnectionFactory;

public class RegisterHouseRateDaoImpl implements RegisterHouseRateDao {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "RegisterHouseRateMapper.create";

	@Override
	public int create(Insurance insurance) {
		return sqlSession.insert(Create, insurance);
	}

}
