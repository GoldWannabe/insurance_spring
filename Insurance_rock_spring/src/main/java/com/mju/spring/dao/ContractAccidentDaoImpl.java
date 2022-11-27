package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ContractAccidentDaoImpl implements ContractAccidentDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String InsertContractAccident = "ContractAccidentMapper.insertContractAccident";

	@Override
	public void insertContractProvision(String[] contractIDAndAccidentID) {
		sqlSession.selectOne(InsertContractAccident, contractIDAndAccidentID);
	}

}
