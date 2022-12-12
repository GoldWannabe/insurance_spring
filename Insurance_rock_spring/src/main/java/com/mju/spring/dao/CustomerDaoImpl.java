package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.contractTeam.contractManagement.CustomerIDAndInsuranceNumDto;
import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;
import com.mju.spring.dto.salesTeam.InsuranceSales.CustomerDto;
import com.mju.spring.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectBankNameAndAccountNum = "CustomerMapper.selectBankNameAndAccountNum";
	private static final String SelectCustomerById = "CustomerMapper.selectCustomerById";
	private static final String SelectCustomerByNameAndPhoneNum = "CustomerMapper.selectCustomerByNameAndPhoneNum";
	private static final String SelectInsuranceNum = "CustomerMapper.selectInsuranceNum";
	private static final String UpdateInsuranceNum = "CustomerMapper.updateInsuranceNum";
	private static final String Create = "CustomerMapper.create";
	private static final String DeleteInsuranceNum = "CustomerMapper.deleteInsuranceNum";
	private static final String SelectCustomerInfo = "CustomerMapper.selectCustomerInfo";
	private static final String UpdateCustomerInfo = "CustomerMapper.updateCustomerInfo";
	
	@Override
	public int create(Customer customer) {
		return sqlSession.insert(Create, customer);
	}
	
	@Override
	public CustomerBankDto retrivecustomerBank(String customerID) {
		return sqlSession.selectOne(SelectBankNameAndAccountNum, customerID);
	}
	
	@Override
	public Customer retriveCustomerByNameAndPhoneNum(CustomerDto customerDTO) {
		return sqlSession.selectOne(SelectCustomerByNameAndPhoneNum, customerDTO);
	}

	@Override
	public Customer retriveCustomerById(String customerID) {
		return sqlSession.selectOne(SelectCustomerById, customerID);
	}

	@Override
	public int updateInsuranceNum(Customer customer) {
		return sqlSession.update(UpdateInsuranceNum, customer);
	}

	@Override
	public Double selectInsuranceNum(String customerID) {
		try {
			return sqlSession.selectOne(SelectInsuranceNum, customerID);
			
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public void updateInsuranceNum(CustomerIDAndInsuranceNumDto customerIDAndInsuranceNumDto) {
		sqlSession.update(UpdateInsuranceNum, customerIDAndInsuranceNumDto);
	}
	
	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public void deleteInsuranceNum(String customerID) {
		sqlSession.delete(DeleteInsuranceNum, customerID);
		
	}

	@Override
	public Customer retriveCustomerInfo(Customer customer) {
		return sqlSession.selectOne(SelectCustomerInfo, customer);
	}

	@Override
	public void updateCustomerInfo(Customer customer) {
		sqlSession.update(UpdateCustomerInfo, customer);
	}
	
}
