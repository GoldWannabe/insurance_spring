package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.Entity.Contract;

@Repository
public class ApplyContractDAOImpl implements ContractDAO {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "ApplyContractMapper.create";

	@Override
	public int create(Contract applyContract) {
		return sqlSession.insert(Create, applyContract);		
	}

	@Override
	public void commit() {
		sqlSession.commit();		
	}

}
