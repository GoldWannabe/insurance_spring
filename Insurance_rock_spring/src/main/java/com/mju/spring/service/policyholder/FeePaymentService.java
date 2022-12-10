package com.mju.spring.service.policyholder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.policyholder.feePayment.DuePaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;

public interface FeePaymentService {

	List<DuePaymentDto> getDuePaymentList(HttpServletRequest request);

	boolean selectDuePayment(int num);

	List<PaymentDto> getPaymentRecord();

	List<ProvisionDto> getProvisionRecord();

}
