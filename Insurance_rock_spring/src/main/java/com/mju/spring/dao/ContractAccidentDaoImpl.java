package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.ConctractAccidentDto;

@Repository
public class ContractAccidentDaoImpl implements ContractAccidentDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String InsertContractAccident = "ContractAccidentMapper.insertContractAccident";

	@Override
	public void insertContractProvision(ConctractAccidentDto contractAccidentDto) {
		sqlSession.selectOne(InsertContractAccident, contractAccidentDto);
		// TODO Auto-generated method stub
		
	}

}
