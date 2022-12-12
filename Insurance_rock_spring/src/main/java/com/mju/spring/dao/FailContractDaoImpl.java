package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.salesTeam.InsuranceSales.FailContractDto;
import com.mju.spring.entity.Contract;

@Repository
public class FailContractDaoImpl implements FailContractDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String Create = "ContractMapper.create";
	private static final String Search = "FailContractMapper.search";
	private static final String Delete = "FailContractMapper.delete";
	
	
	@Override
	public int create(Contract contract) {
		return sqlSession.insert(Create, contract);
	}
	
	@Override
	public List<Contract> retriveFailContract(FailContractDto failContractDTO) {
		return sqlSession.selectList(Search, failContractDTO);
			
	}
	
	@Override
	public int delete(Contract selectedFailContract) {
		return sqlSession.delete(Delete, selectedFailContract);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

}
