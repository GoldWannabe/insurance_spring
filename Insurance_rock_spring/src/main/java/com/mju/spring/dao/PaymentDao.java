package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.entity.Payment;

public interface PaymentDao {

	List<PaymentDto> retrivePayment(PolicyholderDto policyholderDto);

	int insertPayment(Payment payment);

	void commit();

}
