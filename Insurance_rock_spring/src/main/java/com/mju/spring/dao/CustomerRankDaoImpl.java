package com.mju.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.CustomerRankDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;


@Repository
public class CustomerRankDaoImpl implements CustomerRankDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectRenewCustomerRank = "CustomerRankMapper.selectRenewCustomerRank";
	private static final String SelectRankID = "CustomerRankMapper.selectRankID";
	private static final String Create = "CustomerRankMapper.create";

	@Override
	public int create(CustomerRankDto customerRankDTO) {
		return 0;
	}

	@Override
	public void commit() {
		this.sqlSession.commit();
	}

	@Override
	public RenewCustomerRankDto retriveAllId(String contractID) {
		return sqlSession.selectOne(SelectRenewCustomerRank, contractID);
	}

	@Override
	public String retriveRankID(String contractID) {
		return sqlSession.selectOne(SelectRankID, contractID);
	}

	@Override
	public void insertCustomerRank(RenewCustomerRankDto renewCustomerRankDto) {
		sqlSession.insert(Create, renewCustomerRankDto);
		
	}
	
	

}
