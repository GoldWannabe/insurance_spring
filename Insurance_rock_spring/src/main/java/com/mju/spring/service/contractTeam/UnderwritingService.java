package com.mju.spring.service.contractTeam;

import java.util.List;

import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.ReasonDto;
import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyRenewContractDto;
import com.mju.spring.entity.Contract;

public interface UnderwritingService {

	List<ApplyContractDto> getApply();

	List<RenewContractDto> getRenew();

	VerifyApplyContractDto verifyApply();

	VerifyRenewContractDto verifyRenew();

	ReasonDto getReason();

	boolean permitApply();

	boolean notPermitApply();

	boolean permitRenew();

	boolean notPermitRenew();

}
