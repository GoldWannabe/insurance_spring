package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Contract;

@Repository
public class FailContractDaoImpl implements FailContractDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String Create = "ContractMapper.create";
	
	@Override
	public int create(Contract contract) {
		return sqlSession.insert(Create, contract);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}
}
