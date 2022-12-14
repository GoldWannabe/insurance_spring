package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;
import com.mju.spring.dto.salesTeam.InsuranceSales.CustomerRankDto;

@Repository
public class CustomerRankDaoImpl implements CustomerRankDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String SelectRenewCustomerRank = "CustomerRankMapper.selectRenewCustomerRank";
	private static final String SelectRankID = "CustomerRankMapper.selectRankID";
	private static final String Create = "CustomerRankMapper.create";
	private static final String CreateRank = "CustomerRankMapper.createRank";
	private static final String DeleteCustomerRank = "CustomerRankMapper.deleteCustomerRank";

	@Override
	public void commit() {
		this.sqlSession.commit();
	}

	@Override
	public RenewCustomerRankDto retriveAllId(String contractID) {
		try {
			return sqlSession.selectOne(SelectRenewCustomerRank, contractID);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String retriveRankID(String contractID) {
		return sqlSession.selectOne(SelectRankID, contractID);
	}

	@Override
	public void insertCustomerRank(RenewCustomerRankDto renewCustomerRankDto) {
		sqlSession.insert(Create, renewCustomerRankDto);

	}

	@Override
	public List<String> retriveRankIDList(String contractID) {
		return sqlSession.selectList(SelectRankID, contractID);

	}

	@Override
	public int create(CustomerRankDto customerRankDTO) {
		return sqlSession.insert(CreateRank, customerRankDTO);
	}

	@Override
	public int deleteCustomerRank(String rankID) {
		return sqlSession.delete(DeleteCustomerRank, rankID);
	}
	
}
