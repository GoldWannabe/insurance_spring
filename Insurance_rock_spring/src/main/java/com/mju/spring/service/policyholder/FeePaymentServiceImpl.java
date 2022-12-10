package com.mju.spring.service.policyholder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ContractDao;
import com.mju.spring.dao.PaymentDao;
import com.mju.spring.dao.ProvisionDao;
import com.mju.spring.dto.policyholder.feePayment.DuePaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Customer;

@Service
public class FeePaymentServiceImpl implements FeePaymentService {
	
	@Autowired ContractDao contractDao;
	@Autowired PaymentDao paymentDao;
	@Autowired ProvisionDao provisionDao;
	
	private List<DuePaymentDto> duePaymentList;
	private PolicyholderDto policyholderDto;
	private Contract contract;
	private Customer customer;

	@Override
	public List<DuePaymentDto> getDuePaymentList(HttpServletRequest request) {
		policyholderDto = new PolicyholderDto();
		policyholderDto.setCustomerName(request.getParameter("customerName"));
		policyholderDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));
		this.duePaymentList = this.contractDao.retrivePayment(policyholderDto);
		checkFullPayment();
		
		return this.duePaymentList;
	}

	private void checkFullPayment() {
		//일시불 여부
		for(DuePaymentDto duePaymentDto : this.duePaymentList) {
			if(duePaymentDto.getInsuranceFee() <= duePaymentDto.getUnpaidFee()) {
				duePaymentDto.setFullPayment(true);
			} else if(duePaymentDto.getInsuranceFee() > duePaymentDto.getUnpaidFee()) {
				duePaymentDto.setFullPayment(false);
			} else {
				return;
			}
		}
	}

	@Override
	public boolean selectDuePayment(int num) {
		String contractID = duePaymentList.get(num).getContractID();
		String customerID = duePaymentList.get(num).getCustomerID();
		return false;
	}

	@Override
	public List<PaymentDto> getPaymentRecord() {
		return this.paymentDao.retrivePayment(this.policyholderDto);
	}

	@Override
	public List<ProvisionDto> getProvisionRecord() {
		return this.provisionDao.retriveProvision(this.policyholderDto);
	}

}
