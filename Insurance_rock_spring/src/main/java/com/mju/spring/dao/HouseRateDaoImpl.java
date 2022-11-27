package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Insurance;

@Repository
public class HouseRateDaoImpl implements HouseRateDao {
private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectHouseRate = "HouseRateMapper.selectHouseRate";
	private static final String Create = "HouseRateMapper.create";

	@Override
	public List<Double> retriveHouseName(String insuranceID) {
		return sqlSession.selectList(SelectHouseRate, insuranceID);
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
