package com.mju.spring.service.policyholder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.spring.dto.policyholder.feePayment.PaymentDto;

public interface FeePaymentService {

	List<PaymentDto> getPaymentList(HttpServletRequest request);

}
