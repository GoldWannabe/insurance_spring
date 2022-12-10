package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;

@Repository
public class PaymentDaoImpl implements PaymentDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectPayment = "PaymentMapper.selectPayment";

	@Override
	public List<PaymentDto> retrivePayment(PolicyholderDto policyholderDto) {
		return sqlSession.selectList(SelectPayment, policyholderDto);
	}

	


}
