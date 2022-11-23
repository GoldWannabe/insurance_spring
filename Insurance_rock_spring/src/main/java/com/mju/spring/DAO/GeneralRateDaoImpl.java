package com.mju.spring.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralRateDaoImpl implements GeneralRateDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectGeneralRate = "GeneralRateMapper.selectGeneralRate";
	
	@Override
	public List<Double> retriveGeneralRate(String insuranceID) {
		return sqlSession.selectList(SelectGeneralRate, insuranceID); 
	}

}
