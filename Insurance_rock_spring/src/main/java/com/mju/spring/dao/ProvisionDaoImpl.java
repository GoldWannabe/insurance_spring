package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;
import com.mju.spring.entity.Provision;

@Repository
public class ProvisionDaoImpl implements ProvisionDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String InsertProvision = "ProvisionMapper.insertProvision";
	private static final String SelectProvision = "ProvisionMapper.selectProvision";

	@Override
	public void insertProvision(Provision provision) {
		sqlSession.insert(InsertProvision, provision);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public List<ProvisionDto> retriveProvision(PolicyholderDto policyholderDto) {
		return sqlSession.selectList(SelectProvision, policyholderDto);
	}
	
}
