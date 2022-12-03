package com.mju.spring.controller.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.entity.Contract;
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
		if( contractList != null) {
			model.addAttribute("ContractList", contractList);
		}else {
			model.addAttribute("NotContract", "해당 고객님의 계약이 존재하지 않습니다.");
		}

		return "contractTeam//contractManagement//selectContract";
	}
	
	@RequestMapping(value = "selectContract", method = RequestMethod.GET)
	public String selectContract(HttpServletRequest request, Model model) {
		List<ContractManagementAccidentDto> contractAccidentList = this.contractManagementService.searchAccidentHistory(request);
		if( contractAccidentList != null) {
			model.addAttribute("ContractAccidentList", contractAccidentList);
		}else {
			model.addAttribute("NotAccidentHistory", "해당 계약은 사고이력이 존재하지 않습니다.");
		}

		return "contractTeam//contractManagement//selectRenewAndRenewCancel";
	}
	
	@RequestMapping(value = "selectRenewAndRenewCancel", method = RequestMethod.GET)
	public String selectRenewAndRenewCancel(HttpServletRequest request, Model model) {
		if(request.getParameter("RenewMenu").equals("renew")) {
			return "contractTeam//applyRenew//selectRankAndBesides";
		}else if(request.getParameter("RenewMenu").equals("renewCancel")) {
			
			return "contractTeam//applyRenew//";
		}else if(request.getParameter("RenewMenu").equals("cancel")) {
			model.addAttribute("JudgeResult", "홈화면으로 돌아갑니다.");
			return "menu//showResult";	
		}else {
			return "error";
		}
	}
	@RequestMapping(value = "selectRankAndBesides", method = RequestMethod.GET)
	public String selectRankAndBesides(HttpServletRequest request, Model model) {
		if(request.getParameter("renew").equals("rankRenew")) {
			return "contractTeam//applyRenew//applyRankRenew";
		}else if(request.getParameter("renew").equals("BesidesRenew")) {
			return "contractTeam//applyRenew//";
		}else {
			return "error";
		}
	}
	@RequestMapping(value = "applyRankRenew", method = RequestMethod.GET)
	public String applyRankRenew(HttpServletRequest request, Model model) {
		//계약 관리 할 때, 갱신 부분을 할 경우 rank 업데이트 하지 말고 현재 랭크 id 맨 앞에 *붙여서 새로 저장
		 this.contractManagementService.renewRank(request);
		return null;
		
	}
	
}
