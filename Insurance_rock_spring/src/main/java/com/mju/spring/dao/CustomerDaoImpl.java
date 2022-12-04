package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.contractTeam.contractManagement.CustomerIDAndInsuranceNumDto;
import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;
import com.mju.spring.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectBankNameAndAccountNum = "CustomerMapper.selectBankNameAndAccountNum";
	private static final String SelectCustomerById = "CustomerMapper.selectCustomerById";
	private static final String SelectInsuranceNum = "CustomerMapper.selectInsuranceNum";
	private static final String UpdateInsuranceNum = "CustomerMapper.updateInsuranceNum";

	@Override
	public CustomerBankDto retrivecustomerBank(String customerID) {
		return sqlSession.selectOne(SelectBankNameAndAccountNum, customerID);
	}

	@Override
	public Customer retriveCustomerById(String customerID) {
		return sqlSession.selectOne(SelectCustomerById, customerID);
	}

	@Override
	public Double selectInsuranceNum(String customerID) {
		return sqlSession.selectOne(SelectInsuranceNum, customerID);
	}

	@Override
	public void updateInsuranceNum(CustomerIDAndInsuranceNumDto customerIDAndInsuranceNumDto) {
		sqlSession.update(UpdateInsuranceNum, customerIDAndInsuranceNumDto);
	}

	@Override
	public void commit() {
		sqlSession.commit();
		
	}
	
}
