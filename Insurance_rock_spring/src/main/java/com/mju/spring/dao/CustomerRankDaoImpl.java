package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.CustomerRankDto;
import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;


@Repository
public class CustomerRankDaoImpl implements CustomerRankDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectRenewCustomerRank = "CustomerRankMapper.selectRenewCustomerRank";

	@Override
	public int create(CustomerRankDto customerRankDTO) {
		return 0;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RenewCustomerRankDto retriveAllId(String contractID) {
		return sqlSession.selectOne(SelectRenewCustomerRank, contractID);
	}
	
	

}
