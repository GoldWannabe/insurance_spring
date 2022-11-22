package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class HouseRateDaoImpl implements HouseRateDao {
private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectHouseRate = "HouseRateMapper.selectHouseRate";
	

	@Override
	public void retriveHouseName(String insuranceID) {
		System.out.println(insuranceID);
		System.out.println(sqlSession.selectList(SelectHouseRate, "is2"));
	}

}
