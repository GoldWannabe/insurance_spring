package com.mju.spring.controller.policyholder;

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

import com.mju.spring.dto.policyholder.feePayment.AccountDto;
import com.mju.spring.dto.policyholder.feePayment.DuePaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;
import com.mju.spring.dto.policyholder.feePayment.UnpaideFeeDto;
import com.mju.spring.exception.ChangeDateException;
import com.mju.spring.exception.FailPaymentExcaption;
import com.mju.spring.exception.LackMoneyException;
import com.mju.spring.exception.NotFindBank;
import com.mju.spring.exception.NotFindPolicyholderException;
import com.mju.spring.exception.NotFindRecordException;
import com.mju.spring.exception.UnderMinimunMoneyException;
import com.mju.spring.service.policyholder.FeePaymentService;

@Controller
public class FeePaymentControllar {

	@Autowired
	FeePaymentService feePaymentService;

	@RequestMapping(value = "inputPolicyholderInfo", method = RequestMethod.GET)
	public String inputPolicyholderInfo(HttpServletRequest request, Model model) {
		if (request.getParameter("selectPayment").equals("getDuePayment")) {
			return getDuePaymentList(request, model);
		} else if (request.getParameter("selectPayment").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String getDuePaymentList(HttpServletRequest request, Model model) {
		List<DuePaymentDto> duePaymentList = this.feePaymentService.getDuePaymentList(request);
		if (duePaymentList.size() > 0) {
			model.addAttribute("DuePaymentList", duePaymentList);
			return "policyholder//checkInsuranceFee//selectDuePayment";
		} else {
			throw new NotFindPolicyholderException();
		}
	}

	@RequestMapping(value = "selectDuePayment", method = RequestMethod.GET)
	public String selectDuePayment(HttpServletRequest request, Model model) {
		if (request.getParameter("selectPayOrRecord").equals("payInsuranceFee")) {
			return getAccountAndSetCustomer(model);
		} else if (request.getParameter("selectPayOrRecord").equals("showPaymentRecord")) {
			return getPaymentRecord(model);
		} else if (request.getParameter("selectPayOrRecord").equals("showProvisionRecord")) {
			return getProvisionRecord(model);
		} else if (request.getParameter("selectPayOrRecord").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String getAccountAndSetCustomer(Model model) {
		AccountDto accountDto = this.feePaymentService.getAccountAndSetCustomer();
		if (accountDto != null) {
			model.addAttribute("Account", accountDto);
			return "policyholder//selectPaymentMethod//inputAccount";
		} else {
			return "error";
		}
	}

	private String getPaymentRecord(Model model) {
		List<PaymentDto> paymentList = this.feePaymentService.getPaymentRecord();
		if (paymentList.size() > 0) {
			model.addAttribute("PaymentList", paymentList);
			return "policyholder//checkInsuranceFee//showPaymentRecord";
		} else { 
			throw new NotFindRecordException();
		}
	}

	private String getProvisionRecord(Model model) {
		List<ProvisionDto> provisionList = this.feePaymentService.getProvisionRecord();
		if (provisionList.size() > 0) {
			model.addAttribute("ProvisionList", provisionList);
			return "policyholder//checkInsuranceFee//showProvisionRecord";
		} else {			
			throw new NotFindRecordException();
		}
	}

	@RequestMapping(value = "showPaymentRecord", method = RequestMethod.GET)
	public String showPaymentRecord(HttpServletRequest request, Model model) {
		if (request.getParameter("selectCheck").equals("check")) {
			model.addAttribute("JudgeResult", "납부 기록 확인이 완료되었습니다.");
			return "menu//showResult";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "showProvisionRecord", method = RequestMethod.GET)
	public String showProvisionRecord(HttpServletRequest request, Model model) {
		if (request.getParameter("selectCheck").equals("check")) {
			model.addAttribute("JudgeResult", "지급 내역 확인이 완료되었습니다.");
			return "menu//showResult";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "inputAccount", method = RequestMethod.GET)
	public String inputAccount(HttpServletRequest request, Model model) {
		if (request.getParameter("selectCheck").equals("check")) {
			model.addAttribute("JudgeResult", "지급 내역 확인이 완료되었습니다.");
			return setAccount(request, model);
		} else if (request.getParameter("selectCheck").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String setAccount(HttpServletRequest request, Model model) {
		AccountDto accountDto = this.feePaymentService.setAccount(request);
		if ((accountDto != null) && this.feePaymentService.checkBank()) {
			model.addAttribute("Account", accountDto);
			return "policyholder//selectPaymentMethod//checkAccount";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "checkAccount", method = RequestMethod.GET)
	public String checkAccount(HttpServletRequest request, Model model) {
		if (request.getParameter("selectCheck").equals("check")) {
			return "policyholder//feePayment//selectPaymentAmount";
		} else if (request.getParameter("selectCheck").equals("edit")) {
			return editAccount(request, model);
		} else if (request.getParameter("selectCheck").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String editAccount(HttpServletRequest request, Model model) {
		AccountDto accountDto = this.feePaymentService.getAccount();
		if (accountDto != null) {
			model.addAttribute("Account", accountDto);
			return "policyholder//selectPaymentMethod//inputAccount";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "selectPaymentAmount", method = RequestMethod.GET)
	public String selectPaymentAmount(HttpServletRequest request, Model model) {
		if (request.getParameter("selectAmount").equals("full")) {
			return feeFullPayment(model);
		} else if (request.getParameter("selectAmount").equals("part")) {
			return feePartPayment(model);
		} else if (request.getParameter("selectAmount").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String feeFullPayment(Model model) {
		if (this.feePaymentService.feeFullPayment()) {

			model.addAttribute("CheckPrint", "납부가 완료 되었습니다. 납부확인서를 출력하시겠습니까?");
			return "policyholder//feePayment//selectPrint";
		} else {

			return "error";
		}
	}

	private String feePartPayment(Model model) {
		List<DuePaymentDto> duePaymentList = this.feePaymentService.getDuePaymentList();
		if (duePaymentList.size() > 0) {
			model.addAttribute("PaymentList", duePaymentList);
			return "policyholder//feePayment//selectPayment";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "selectPayment", method = RequestMethod.GET)
	public String selectPayment(HttpServletRequest request, Model model) {

		if (this.feePaymentService.feePartPayment(request)) {
			UnpaideFeeDto unpaideFeeDto = this.feePaymentService.getUnpaideFeeDto();
			model.addAttribute("CheckPrint", "납부가 완료 되었습니다. 납부확인서를 출력하시겠습니까? 미납금: " + unpaideFeeDto.getUnpaidFee());
			return "policyholder//feePayment//selectPrint";
		} else {

			return "error";
		}
	}

	@RequestMapping(value = "selectPrint", method = RequestMethod.GET)
	public String selectPrint(HttpServletRequest request, Model model) {
		if (request.getParameter("printPayment").equals("print")) {
			return printPayment();
		} else if (request.getParameter("printPayment").equals("cancel")) {
			return "menu//menu";
		} else {
			return "error";
		}
	}

	private String printPayment() {
		if (this.feePaymentService.printPayment()) {
			return "menu//menu";
		}
		return "error";
	}

	@ExceptionHandler(NotFindPolicyholderException.class)
	private ModelAndView handleFileAcceptException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

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
	@ExceptionHandler(ChangeDateException.class)
	private ModelAndView handleChangeDateException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

	}
	@ExceptionHandler(NotFindBank.class)
	private ModelAndView handleNotFindBank(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

	}
	@ExceptionHandler(UnderMinimunMoneyException.class)
	private ModelAndView handleUnderMinimunMoneyException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

	}
	@ExceptionHandler(LackMoneyException.class)
	private ModelAndView handleLackMoneyException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("policyholder//feePayment//selectPaymentAmount");
		modelAndView.addObject("Popup", true);
		modelAndView.addObject("Message", e.getMessage());
		return modelAndView;

	}
	@ExceptionHandler(FailPaymentExcaption.class)
	private ModelAndView handleFailPaymentExcaption(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", e.getMessage());
		return modelAndView;

	}
}
