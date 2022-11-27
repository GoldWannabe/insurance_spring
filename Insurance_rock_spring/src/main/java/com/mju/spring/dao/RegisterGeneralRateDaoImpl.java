package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.mju.spring.entity.Insurance;

@Repository
public class RegisterGeneralRateDaoImpl implements RegisterGeneralRateDao {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "RegisterGeneralRateMapper.create";
	private static final String SelectRate = "RegisterGeneralRateMapper.selectRate";
	private static final String Delete = "RegisterGeneralRateMapper.delete";

	@Override
	public int create(Insurance insurance) {
		return sqlSession.insert(Create, insurance);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public List<Double> retriveById(String insuranceID) {
		return sqlSession.selectList(SelectRate, insuranceID);
	}

	@Override
	public int delete(String insuranceID) {
		return sqlSession.delete(Delete, insuranceID);
	}

}
