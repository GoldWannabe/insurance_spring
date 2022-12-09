package com.mju.spring.service.policyholder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ContractDao;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;

@Service
public class FeePaymentServiceImpl implements FeePaymentService {
	
	@Autowired ContractDao contractDao;
	
	private List<PaymentDto> paymentList;

	@Override
	public List<PaymentDto> getPaymentList(HttpServletRequest request) {
		PolicyholderDto policyholderDto = new PolicyholderDto();
		policyholderDto.setCustomerName(request.getParameter("customerName"));
		policyholderDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));
		this.paymentList = this.contractDao.retrivePayment(policyholderDto);
		checkFullPayment();
		
		return this.paymentList;
	}

	private void checkFullPayment() {
		//일시불 여부
		for(PaymentDto paymentDto : this.paymentList) {
			if(paymentDto.getInsuranceFee() <= paymentDto.getUnpaidFee()) {
				paymentDto.setFullPayment(true);
			} else if(paymentDto.getInsuranceFee() > paymentDto.getUnpaidFee()) {
				paymentDto.setFullPayment(false);
			} else {
				return;
			}
		}
	}

}
