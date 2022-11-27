package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;

import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;

public class CustomerDaoImpl implements CustomerDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String selectBankNameAndAccountNum = "CustomerMapper.selectBankNameAndAccountNum";

	@Override
	public CustomerBankDto retrivecustomerBank(String customerID) {
		return sqlSession.selectOne(selectBankNameAndAccountNum, customerID);
	}
	
}
