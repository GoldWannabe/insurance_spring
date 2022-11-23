package com.mju.spring.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class HouseRateDaoImpl implements HouseRateDao {
private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectHouseRate = "HouseRateMapper.selectHouseRate";
	

	@Override
	public List<Double> retriveHouseName(String insuranceID) {
		return sqlSession.selectList(SelectHouseRate, insuranceID);
	}

}
