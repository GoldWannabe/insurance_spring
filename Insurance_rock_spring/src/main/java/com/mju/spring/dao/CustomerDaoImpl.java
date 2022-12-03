package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;
import com.mju.spring.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectBankNameAndAccountNum = "CustomerMapper.selectBankNameAndAccountNum";
	private static final String SelectCustomerById = "CustomerMapper.selectCustomerById";

	@Override
	public CustomerBankDto retrivecustomerBank(String customerID) {
		return sqlSession.selectOne(SelectBankNameAndAccountNum, customerID);
	}

	@Override
	public Customer retriveCustomerById(String customerID) {
		return sqlSession.selectOne(SelectCustomerById, customerID);
	}
	
}
