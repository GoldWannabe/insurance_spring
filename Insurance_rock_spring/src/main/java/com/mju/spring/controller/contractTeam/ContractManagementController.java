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

import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerPopupDto;
import com.mju.spring.dto.contractTeam.contractManagement.InsuranceDetailsDto;
import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;
import com.mju.spring.exception.NonCustomerContractException;
import com.mju.spring.exception.NonExistContractException;
import com.mju.spring.service.contractTeam.ContractManagementService;

@Controller
public class ContractManagementController {
	
	@Autowired
	ContractManagementService contractManagementService;
	
	@RequestMapping(value = "selectContractSearchAndCancel", method = RequestMethod.GET)
	public String selectContractSearch(HttpServletRequest request, Model model) {
		if(request.getParameter("search").equals("search")) {
			return "contractTeam//contractManagement//inputNameAndPhoneNum";			
		}else if(request.getParameter("search").equals("cancel")) {
			model.addAttribute("JudgeResult", "홈화면으로 돌아갑니다.");
			return "menu//showResult";		
		}else {
			return "error";
		}

	}
	
	@RequestMapping(value = "inputNameAndPhoneNum", method = RequestMethod.GET)
	public String inputNameAndPhoneNum(HttpServletRequest request, Model model) {
		List<Contract> contractList  = this.contractManagementService.contractSearch(request);
		if( !contractList.isEmpty()) {
			model.addAttribute("ContractList", contractList);
		}else {
			throw new NonExistContractException();
		}

		return "contractTeam//contractManagement//selectContract";
	}
	
	@RequestMapping(value = "selectContract", method = RequestMethod.GET)
	public String selectContract(HttpServletRequest request, Model model) {
		InsuranceDetailsDto insuranceDetailsDto = this.contractManagementService.searchInsuranceDetails(request);
		List<Accident> accidentList = this.contractManagementService.searchAccidentHistory(request);
		
		if( !accidentList.isEmpty() && insuranceDetailsDto !=null) {
			model.addAttribute("ContractID", insuranceDetailsDto.getContractID());
			model.addAttribute("InsuranceName", insuranceDetailsDto.getInsuranceName());
			model.addAttribute("CustomerName", insuranceDetailsDto.getCustomerName());
			model.addAttribute("CustomerPhoneNum", insuranceDetailsDto.getCustomerPhoneNum());
			model.addAttribute("StartDate", insuranceDetailsDto.getStartDate());
			model.addAttribute("EndDate", insuranceDetailsDto.getEndDate());
			model.addAttribute("SecurityFee", insuranceDetailsDto.getSecurityFee());
			model.addAttribute("InsuranceFee", insuranceDetailsDto.getInsuranceFee());
			model.addAttribute("PaymentCycle", insuranceDetailsDto.getPaymentCycle());
			model.addAttribute("UnpaidFee", insuranceDetailsDto.getUnpaidFee());
			model.addAttribute("Material", insuranceDetailsDto.getMaterial());
			model.addAttribute("FireFacilities", insuranceDetailsDto.getFireFacilities());
			model.addAttribute("Height", insuranceDetailsDto.isHeight());
			model.addAttribute("Scale", insuranceDetailsDto.getScale());
			model.addAttribute("SurroundingFacilities", insuranceDetailsDto.getSurroundingFacilities());
			model.addAttribute("Purpose", insuranceDetailsDto.getPurpose());
			model.addAttribute("AccidentList", accidentList);
		}else {
			model.addAttribute("JudgeResult", "해당 계약은 이미 갱신 신청 접수가 되어 갱신 혹은  해지할수없습니다. 다른 계약을 선택하시거나 고객센터(010-1234-5678)로 문의주기실 바랍니다.");	
			return "menu//showResult";	
		}
		return "contractTeam//contractManagement//selectRenewAndRenewCancel";

	}
	
	@RequestMapping(value = "selectRenewAndRenewCancel", method = RequestMethod.GET)
	public String selectRenewAndRenewCancel(HttpServletRequest request, Model model) {
		if(request.getParameter("RenewMenu").equals("renew")) {
			InsuranceDetailsDto insuranceDetailsDto = this.contractManagementService.getRenewInfo();
			
			model.addAttribute("FireFacilities", insuranceDetailsDto.getFireFacilities());
			model.addAttribute("Scale", insuranceDetailsDto.getScale());
			model.addAttribute("SurroundingFacilities", insuranceDetailsDto.getSurroundingFacilities());
			model.addAttribute("Height", insuranceDetailsDto.isHeight());
			model.addAttribute("Material", insuranceDetailsDto.getMaterial());
			model.addAttribute("Purpose",insuranceDetailsDto.getPurpose());
			model.addAttribute("SecurityFee",insuranceDetailsDto.getSecurityFee());
			model.addAttribute("InsuranceFee", insuranceDetailsDto.getInsuranceFee());
			model.addAttribute("PaymentCycle", insuranceDetailsDto.getPaymentCycle());
			
			return "contractTeam//contractManagement//applyRenew";
		}else if(request.getParameter("RenewMenu").equals("renewCancel")) {
			RenewCustomerPopupDto renewCustomerPopupDto = this.contractManagementService.cancelRenew(request);
			if( renewCustomerPopupDto != null ) {
				if(renewCustomerPopupDto.getCustomerName().equals("고객이가입한보험없음")) {
					throw new NonCustomerContractException();
				
				}else {
					model.addAttribute("CustomerName", renewCustomerPopupDto.getCustomerName());
					model.addAttribute("InsuranceName", renewCustomerPopupDto.getInsuranceName());
					model.addAttribute("CustomerPhoneNum", renewCustomerPopupDto.getCustomerPhoneNum());
					return "contractTeam//contractManagement//showCancelRenew";
				}
			}else {
				model.addAttribute("JudgeResult", "해당 계약은 이미 갱신 신청 접수가 되어 해지할수없습니다. 다른 계약을 선택하시거나 고객센터(010-1234-5678)로 문의주기실 바랍니다.");	
				return "menu//showResult";	
			}
			
		}else if(request.getParameter("RenewMenu").equals("cancel")) {
			model.addAttribute("JudgeResult", "홈화면으로 돌아갑니다.");
			return "menu//showResult";	
		}else {
			return "error";
		}
	}

	@RequestMapping(value = "applyRenew", method = RequestMethod.GET)
	public String applyRenew(HttpServletRequest request, Model model) {
		//계약 관리 할 때, 갱신 부분을 할 경우 rank 업데이트 하지 말고 현재 랭크 id 맨 앞에 *붙여서 새로 저장 //이미 갱신완료된 계약의 경우 제외할것.
		boolean cheakRenew = this.contractManagementService.applyRenew(request);
		if(cheakRenew) {
			model.addAttribute("JudgeResult", "계약 갱신 신청이 완료되었습니다.");			
			return "menu//showResult";	
		}else {
			model.addAttribute("JudgeResult", "해당 계약은 이미 갱신 신청 접수가 된 계약입니다. 다른 계약을 선택하시거나 고객센터(010-1234-5678)로 문의주기실 바랍니다.");	
			return "menu//showResult";	
		}
		
		
	}
	
//	E2. DB 접근에 실패한 경우
	@ExceptionHandler(PersistenceException.class)
	private ModelAndView persistenceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", "DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		return modelAndView;
	}
	/////////////////계약관리를하다////////////////////////
	//E1.해당 계약이 없을 경우
	@ExceptionHandler(NonExistContractException.class)
	private ModelAndView NonExistContractException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("contractTeam//contractManagement//selectContract");
		modelAndView.addObject("NotContract", "해당 고객이 가입한 보험이 존재하지 않습니다. 다시 입력해주세요.");
		return modelAndView;
	}
	//E4.고객이 없는 경우
		@ExceptionHandler(NonCustomerContractException.class)
		private ModelAndView NonCustomerContractException(Exception e) {
			System.err.println(e.getMessage());
			ModelAndView modelAndView= new ModelAndView();
			modelAndView.setViewName("menu//showResult");
			modelAndView.addObject("JudgeResult",  "이 보험에 계약된 고객이 조회되지 않습니다.");
			return modelAndView;
		}
		
	
	
}
