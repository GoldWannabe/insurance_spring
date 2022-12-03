package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.ContractProvisionDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectContractDto;
import com.mju.spring.dto.damageAssessment.compansate.UpdateContractDto;
import com.mju.spring.entity.Contract;


@Repository
public class ApplyContractDAOImpl implements ContractDao {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String Create = "ApplyContractMapper.create";

	public int create(Contract applyContract) {
		return sqlSession.insert(Create, applyContract);		
	}

	@Override
	public void commit() {
		sqlSession.commit();		
	}

	@Override
	public List<Contract> retriveNameAndPhoneNum(SelectContractDto selectContractDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractProvisionDto retriveContract(String contractID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateContractProvisionFee(UpdateContractDto updateContractDto) {
		// TODO Auto-generated method stub
		
	}

}
