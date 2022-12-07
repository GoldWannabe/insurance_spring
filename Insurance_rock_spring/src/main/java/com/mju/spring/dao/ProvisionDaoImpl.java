package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Provision;

@Repository
public class ProvisionDaoImpl implements ProvisionDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String InsertProvision = "ProvisionMapper.insertProvision";

	@Override
	public void insertProvision(Provision provision) {
		sqlSession.insert(InsertProvision, provision);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}
	
}
