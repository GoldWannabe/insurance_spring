package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Insurance;

@Repository
public class GeneralRateDaoImpl implements GeneralRateDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectGeneralRate = "GeneralRateMapper.selectGeneralRate";
	private static final String Create = "GeneralRateMapper.create";

	@Override
	public List<Double> retriveGeneralRate(String insuranceID) {
		return sqlSession.selectList(SelectGeneralRate, insuranceID); 
	}

	@Override
	public int create(Insurance insurance) {
		return sqlSession.insert(Create, insurance);
	}
	
	@Override
	public void commit() {
		this.sqlSession.commit();
	}

}
