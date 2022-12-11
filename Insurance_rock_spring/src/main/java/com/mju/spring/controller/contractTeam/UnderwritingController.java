package com.mju.spring.controller.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyRenewContractDto;
import com.mju.spring.exception.NotFindJoinedCustomerException;
import com.mju.spring.exception.NotFindJudgeContractException;
import com.mju.spring.dto.contractTeam.Underwriting.ReasonDto;
import com.mju.spring.service.contractTeam.UnderwritingService;

@Controller
public class UnderwritingController {

	@Autowired
	UnderwritingService underwritingService;

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
		if (applyContractList.size() > 0) {
			model.addAttribute("ApplyContractList", applyContractList);
			return "contractTeam//underwriting//selectApply";
		} else {
			throw new NotFindJudgeContractException();
		}
	}

	@RequestMapping(value = "selectApply", method = RequestMethod.GET)
	public String selectApply(HttpServletRequest request, Model model) {
		return verifyApply(request, model);
	}

	private String verifyApply(HttpServletRequest request, Model model) {
		VerifyApplyContractDto verifyApplyContract = this.underwritingService.verifyApply(request); // 저장될 정보와 실패했을 경우
		if (verifyApplyContract == null) {

			return notPermitApply(model);
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
			this.underwritingService.setReason(request);
			return notPermitApply(model);
		} else if (request.getParameter("selectPerit").equals("cancel")) {
			model.addAttribute("JudgeResult", "진행중인 검증을 취소하셨습니다");
			return "menu//showResult";
		} else {
			return "error";
		}

	}

	private String permitApply(Model model) {
		if (this.underwritingService.permitApply()) {
			return "contractTeam//applyResult//selectContinue";
		} else {
			return "error";
		}
	}

	private String notPermitApply(Model model) {
		if (this.underwritingService.notPermitApply()) {
			ReasonDto reasonDto = this.underwritingService.getReason();
			String reason = "계약이 반려되었습니다. 반려사유: " + reasonDto.getReason();
			model.addAttribute("JudgeResult", reason);
			return "menu//showResult";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "selectContinue", method = RequestMethod.GET)
	public String selectContinue(HttpServletRequest request) {
		if (request.getParameter("continue").equals("yes")) {
			return "contractTeam//underwriting//selectUnderwrite";
		} else if (request.getParameter("continue").equals("no")) {
			return "menu//menu";
		} else {
			return "error";
		}

	}

	private String getRenew(Model model) {
		List<RenewContractDto> renewContractList = this.underwritingService.getRenew();
		if (renewContractList.size() > 0) {
			model.addAttribute("RenewContractList", renewContractList);
			return "contractTeam//underwriting//selectRenew";
		} else {
			throw new NotFindJudgeContractException();
		}
	}

	@RequestMapping(value = "selectRenew", method = RequestMethod.GET)
	public String selectRenew(HttpServletRequest request, Model model) {
		return verifyRenew(request, model);
	}

	private String verifyRenew(HttpServletRequest request, Model model) {
		VerifyRenewContractDto verifyRenewContract = this.underwritingService.verifyRenew(request);
		if (verifyRenewContract == null) {
			return notPermitRenew(model);
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
			this.underwritingService.setReason(request);
			return notPermitRenew(model);
		} else if (request.getParameter("selectPerit").equals("cancel")) {
			model.addAttribute("JudgeResult", "진행중인 검증을 취소하셨습니다");
			return "menu//showResult";
		} else {
			return "error";
		}

	}

	private String permitRenew(Model model) {
		if (this.underwritingService.permitRenew()) {
			return "contractTeam//applyResult//selectContinue";
		} else {
			return "error";
		}
	}

	private String notPermitRenew(Model model) {
		if (this.underwritingService.notPermitRenew()) {
			ReasonDto reasonDto = this.underwritingService.getReason();
			// System.out.println("계약이 반려되었습니다." + System.lineSeparator() + "반려사유: " +
			// reasonDto.getReason());
			String reason = "계약이 반려되었습니다. 반려사유: " + reasonDto.getReason();
			model.addAttribute("JudgeResult", reason);
			return "menu//showResult";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "underwriteCancel", method = RequestMethod.GET)
	public String cancelUnderwrite() {
		return "menu//menu";

	}

	@ExceptionHandler(PersistenceException.class)
	private ModelAndView handlerPersistenceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult",
				"DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		return modelAndView;

	}

	@ExceptionHandler(NotFindJudgeContractException.class)
	private ModelAndView handlerNotFindJudgeContractException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

	}
	
	@ExceptionHandler(NotFindJoinedCustomerException.class)
	private ModelAndView handlerNotFindJoinedCustomerException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

	}
}
