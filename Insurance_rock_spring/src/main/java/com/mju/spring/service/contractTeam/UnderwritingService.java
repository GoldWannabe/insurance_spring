package com.mju.spring.service.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.ReasonDto;
import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyRenewContractDto;

public interface UnderwritingService {

	List<ApplyContractDto> getApply();

	List<RenewContractDto> getRenew();

	VerifyApplyContractDto verifyApply(HttpServletRequest request);

	VerifyRenewContractDto verifyRenew(HttpServletRequest request);

	ReasonDto getReason();

	boolean permitApply();

	boolean notPermitApply();

	boolean permitRenew();

	boolean notPermitRenew();

	void setReason(HttpServletRequest request);

}
