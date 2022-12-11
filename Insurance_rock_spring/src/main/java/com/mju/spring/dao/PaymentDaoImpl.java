package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.entity.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectPayment = "PaymentMapper.selectPayment";
	private static final String Create = "PaymentMapper.create";

	@Override
	public List<PaymentDto> retrivePayment(PolicyholderDto policyholderDto) {
		return sqlSession.selectList(SelectPayment, policyholderDto);
	}

	@Override
	public int insertPayment(Payment payment) {
		return sqlSession.insert(Create, payment);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	


}
