package com.mju.spring.controller.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyRenewContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.ReasonDto;
import com.mju.spring.service.contractTeam.UnderwritingService;

@Controller
public class UnderwritingController {

	@Autowired
	UnderwritingService underwritingService;
//selectUnderwrite 
	// 신규인지 갱신인지 선택(취소)
//selectApply
	// 지원한 계약들 중 선택
	// 보험이름 가입자명 연락처 담보액 보험료 가입기간(달)
	// 검증, 취소

//verifyInsurance
	// if (!getInsurance() || !getCustomer())
	// 해당하는 보험, 고객 가져옴
	// verifyPeriod 장기계약 가능한 보험인지
	// verifyPremium
	// getMaterialRank
	// verifyInsuranceFee 최종 요율 계산

	@RequestMapping(value = "selectUnderwrite", method = RequestMethod.GET)
	public String selectUnderwrite(HttpServletRequest request, Model model) {
		if (request.getParameter("underwriteMenu").equals("apply")) {
			return getApply(model);
		} else if (request.getParameter("underwriteMenu").equals("renew")) {
			return getRenew(model);
		} else if (request.getParameter("underwriteMenu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String getApply(Model model) {
		List<ApplyContractDto> applyContractList = this.underwritingService.getApply();
		model.addAttribute("ApplyContractList", applyContractList);
		return "contractTeam//underwriting//selectApply";
	}

	@RequestMapping(value = "selectApply", method = RequestMethod.GET)
	public String selectApply(HttpServletRequest request, Model model) {
		if (request.getParameter("selectVerify").equals("verify")) {
			return verifyApply(request, model);
		} else if (request.getParameter("selectVerify").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}

	}

	private String verifyApply(HttpServletRequest request, Model model) {
		VerifyApplyContractDto verifyApplyContract = this.underwritingService.verifyApply(request); // 저장될 정보와 실패했을 경우
		if (verifyApplyContract == null) {
			ReasonDto reasonDto = this.underwritingService.getReason();
			model.addAttribute("JudgeResult",
					"계약이 반려되었습니다." + System.lineSeparator() + "반려사유: " + reasonDto.getReason());
			return "menu//showResult";
		}
		model.addAttribute("ApplyContract", verifyApplyContract);
		model.addAttribute("Rank", verifyApplyContract.getRank());

		return "contractTeam//applyVerify//selectApplyPermit";
	}

	@RequestMapping(value = "selectApplyPermit", method = RequestMethod.GET)
	public String selectApplyPermit(HttpServletRequest request, Model model) {

		if (request.getParameter("selectPerit").equals("permit")) {
			return permitApply(model);
		} else if (request.getParameter("selectPerit").equals("notPermit")) {
			return notPermitApply(model);
		} else if (request.getParameter("selectPerit").equals("cancel")) {
			model.addAttribute("JudgeResult", "취소되었습니다.");
			return "financialDirector//insuranceJudge//showJudgeResult";
		} else {
			return "error";
		}

	}

	private String permitApply(Model model) {
		if (this.underwritingService.permitApply()) {
			model.addAttribute("JudgeResult", "계약되었습니다.");
		} else {
			return "error";
		}
		return null;
	}

	private String notPermitApply(Model model) {
		if (this.underwritingService.notPermitApply()) {
			model.addAttribute("JudgeResult", "반려되었습니다.");
		} else {
			return "error";
		}
		return null;
	}

	private String getRenew(Model model) {
		List<RenewContractDto> renewContractList = this.underwritingService.getRenew();
		model.addAttribute("RenewContractList", renewContractList);
		return "contractTeam//underwriting//selectApplyRenew";
	}

	@RequestMapping(value = "selectRenew", method = RequestMethod.GET)
	public String selectRenew(HttpServletRequest request, Model model) {
		if (request.getParameter("selectVerify").equals("verify")) {
			return verifyRenew(model);
		} else if (request.getParameter("selectVerify").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String verifyRenew(Model model) {
		VerifyRenewContractDto verifyRenewContract = this.underwritingService.verifyRenew();
		if (verifyRenewContract == null) {
			ReasonDto reasonDto = this.underwritingService.getReason();
			model.addAttribute("JudgeResult",
					"계약이 반려되었습니다." + System.lineSeparator() + "반려사유: " + reasonDto.getReason());
			return "menu//showResult";
		}
		model.addAttribute("RenewContract", verifyRenewContract);
		model.addAttribute("PreviousRank", verifyRenewContract.getPreviousRank());
		model.addAttribute("newRank", verifyRenewContract.getNewRank());

		return "contractTeam//renewVerify//selectRenewPermit";
	}

	@RequestMapping(value = "selectRenewPermit", method = RequestMethod.GET)
	public String selectRenewPermit(HttpServletRequest request, Model model) {
		if (request.getParameter("selectPerit").equals("permit")) {
			return permitRenew(model);
		} else if (request.getParameter("selectPerit").equals("notPermit")) {
			return notPermitRenew(model);
		} else if (request.getParameter("selectPerit").equals("cancel")) {
			model.addAttribute("JudgeResult", "취소되었습니다.");
			return "financialDirector//insuranceJudge//showJudgeResult";
		} else {
			return "error";
		}

	}

	private String permitRenew(Model model) {
		if (this.underwritingService.permitRenew()) {
			model.addAttribute("JudgeResult", "계약되었습니다.");
		} else {
			return "error";
		}
		return null;
	}

	private String notPermitRenew(Model model) {
		if (this.underwritingService.notPermitRenew()) {
			model.addAttribute("JudgeResult", "반려되었습니다.");
		} else {
			return "error";
		}
		return null;
	}
}
