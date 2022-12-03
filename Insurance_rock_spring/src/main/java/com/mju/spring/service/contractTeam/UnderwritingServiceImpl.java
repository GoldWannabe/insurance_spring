package com.mju.spring.service.contractTeam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ApplyContractDao;
import com.mju.spring.dao.RenewContractDao;
import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.ReasonDto;
import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyRenewContractDto;
import com.mju.spring.entity.Contract;

@Service
public class UnderwritingServiceImpl implements UnderwritingService {
	
	@Autowired ApplyContractDao applyContractDao;
	@Autowired RenewContractDao renewContractDao;
	private Contract contract;

	@Override
	public List<ApplyContractDto> getApply() {
		return this.applyContractDao.retriveApplyContractList();
		
	}

	@Override
	public List<RenewContractDto> getRenew() {
		return this.renewContractDao.retriveRenewContractList();
	}

	@Override
	public VerifyApplyContractDto verifyApply() {
		return null;		
	}

	@Override
	public VerifyRenewContractDto verifyRenew() {
		return null;
	}

	@Override
	public ReasonDto getReason() {
		ReasonDto reasonDto = new ReasonDto();
		reasonDto.setReason(this.contract.getReason());
		return reasonDto;
	}

	@Override
	public boolean permitApply() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notPermitApply() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean permitRenew() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notPermitRenew() {
		// TODO Auto-generated method stub
		return false;
	}

}
