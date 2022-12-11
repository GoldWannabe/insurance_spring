package com.mju.spring.service.policyholder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.policyholder.feePayment.AccountDto;
import com.mju.spring.dto.policyholder.feePayment.DuePaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;
import com.mju.spring.dto.policyholder.feePayment.UnpaideFeeDto;

public interface FeePaymentService {

	List<DuePaymentDto> getDuePaymentList(HttpServletRequest request);

	AccountDto getAccountAndSetCustomer();

	List<PaymentDto> getPaymentRecord();

	List<ProvisionDto> getProvisionRecord();

	AccountDto setAccount(HttpServletRequest request);

	boolean checkBank();

	AccountDto getAccount();

	boolean feeFullPayment();

	List<DuePaymentDto> getDuePaymentList();

	boolean feePartPayment(HttpServletRequest request);

	boolean printPayment();

	UnpaideFeeDto getUnpaideFeeDto();

}
