package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;
import com.mju.spring.entity.Provision;

public interface ProvisionDao {
	public void insertProvision(Provision provision);

	public void commit();

	public List<ProvisionDto> retriveProvision(PolicyholderDto policyholderDto);

	
}
