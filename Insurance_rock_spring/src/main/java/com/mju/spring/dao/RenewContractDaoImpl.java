package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewContractManagementDto;

@Repository
public class RenewContractDaoImpl implements RenewContractDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String insertRenewContract = "RenewContractMapper.insertRenewContract";
	private static final String SelectAll = "RenewContractMapper.selectAll";
	private static final String DeleteRenew = "RenewContractMapper.deleteRenew";
	
	@Override
	public List<RenewContractDto> retriveRenewContractList() {
		return sqlSession.selectList(SelectAll);
	}

	@Override
	public void insertApplyRenew(RenewContractManagementDto renewContractManagementDto) {
		sqlSession.selectOne(insertRenewContract, renewContractManagementDto);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public int deleteRenew(String contractID) {
		return sqlSession.delete(DeleteRenew, contractID);
	}

}
