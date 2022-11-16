package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.Entity.Insurance;
import com.mju.spring.Mybatis.MyBatisConnectionFactory;

@Repository
public class InsuranceDAOImpl implements InsuranceDAO {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectName = "InsuranceMapper.selectName";

	@Override
	public Insurance retriveName(String insuranceName) {
		return sqlSession.selectOne(SelectName, insuranceName);
	}

}
