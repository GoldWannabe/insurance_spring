package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.dto.damageAssessment.compansate.ContractAccidentDto;


@Repository
public class ContractAccidentDaoImpl implements ContractAccidentDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String InsertContractAccident = "ContractAccidentMapper.insertContractAccident";
	private static final String SelectContractManagementAccident = "ContractAccidentMapper.selectContractManagementAccident";

	@Override
	public void insertContractProvision(ContractAccidentDto contractAccidentDto) {
		sqlSession.insert(InsertContractAccident, contractAccidentDto);
		
	}

	@Override
	public void commit() {
		sqlSession.commit();
		
	}

	@Override
	public List<ContractManagementAccidentDto> retriveContractAccident(String contractID) {
		return sqlSession.selectList(SelectContractManagementAccident, contractID);
	}

}
