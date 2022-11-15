package com.mju.spring.Controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Service.InsuranceDesignService;
import com.mju.spring.VO.InsuranceVO;

@Controller
public class InsuranceDesignController {
	
	@Autowired
	InsuranceDesignService insuranceDesignService;
	
	private InsuranceVO insuranceVO;
	private InsuranceDTO insuranceDTO;
	
	@RequestMapping(value = "design2", method = RequestMethod.GET) // design2에서 장기여부, 보험종류 정보 제출.
	public String insuranceDesign2(HttpServletRequest request, Model model) {
		//중간 작업 불러오기. 
		//InsuranceDTO insuranceDTO = new InsuranceDTO();
//		if(request.getParameter("longTerm").equals(true)) {
//			insuranceDTO.setLongTerm(true);
//		}else if(request.getParameter("longTerm").equals("단기")) {//term이라는 체크박스 네임 태그에서 장기면 단기면.
//			insuranceDTO.setLongTerm(false);
//		}
//		getParameterValues String[]
//		getParameter String
		
		this.insuranceDTO = insuranceDesignService.getinsuranceTypeAndTerm(request); 
		
		this.insuranceVO = new InsuranceVO();
		insuranceVO.setLongTerm(insuranceDTO.isLongTerm());
		insuranceVO.setInsuranceType(insuranceDTO.getInsuranceType());
		
		model.addAttribute("LongTerm", this.insuranceVO.isLongTerm()); //마지막에 보여주는 화면에 대한 내용 보내주기.
		model.addAttribute("InsuranceType", this.insuranceVO.getInsuranceType());
		
		return "design2";

	}
	@RequestMapping(value = "design3", method = RequestMethod.GET)
	public String insuranceDesign3(HttpServletRequest request, Model model) { //design3에서 
//		String[] text =  request.getParameterValues("text");//순서대로 배열에 들어간다네? 안되면 각각 하나씩.
		this.insuranceDTO = insuranceDesignService.checkName(request); //중복확인 이때 체크하면서 보험 정볻 다 DTO에 셋해줘.
	
		//중복확인
		if(this.insuranceDTO == null) {
			model.addAttribute("overlapError", null);
			return "design3";
			
		}else {
//		보험이름,특약,가입조건,보상조건,설명
			this.insuranceVO.setInsuranceName(this.insuranceDTO.getInsuranceName());
			this.insuranceVO.setSpecialContract(this.insuranceDTO.getSpecialContract());
			this.insuranceVO.setApplyCondition(this.insuranceDTO.getApplyCondition());
			this.insuranceVO.setCompensateCondition(this.insuranceDTO.getCompensateCondition());
			this.insuranceVO.setExplanation(this.insuranceDTO.getExplanation());
			
			model.addAttribute("insuranceName", this.insuranceVO.getInsuranceName());
			model.addAttribute("specialContract", this.insuranceVO.getSpecialContract());
			model.addAttribute("applyCondition",this.insuranceDTO.getApplyCondition());
			model.addAttribute("compensateCondition",this.insuranceDTO.getCompensateCondition());
			model.addAttribute("explanation",this.insuranceDTO.getExplanation());	
		}
		
		return "design3";
	}
	@RequestMapping(value = "popup", method = RequestMethod.GET)
	public String StandardFeePopupDesign(HttpServletRequest request, Model model) {
		this.insuranceDTO = this.insuranceDesignService.getStandardFee(request);//기존 요율별로 기준보험료 측정된거  
		
		this.insuranceVO.setStandardFee(this.insuranceDTO.getStandardFee());
		model.addAttribute("StandardFee", this.insuranceDTO.getStandardFee());
		return "popup";
	}
	
	@RequestMapping(value = "popup2", method = RequestMethod.GET)
	public String rateByGrade(HttpServletRequest request, Model model) {
		//요율 배열로.
		this.insuranceDTO = this.insuranceDesignService.checkRate(request);
		
		return "popup2";
	}
	public void temp(HttpServletRequest request) {
		//제일 시작부분
		//임시 저장한 파일이 있는지 확인
		this.insuranceDTO = insuranceDesignService.getTempInsurance(request);
		
//		this.insuranceDTO = insuranceDesignService.checkName();
		//DTO로 받는다 요율까지
		//실패하면 다시
		
		//view 기본 요율 사용 여부 확인 
		//예 누르면 기본 요율 계산후 최종 등록할건지 말건지. getStandardFee
		//아니오 누르면 직접 입력
//		this.insuranceDTO = insuranceDesignService.checkRate();
		//실패하면 다시
		//성공하면 최종 등록
		
		
		
		//예 누르면 등록
		 boolean a1 = insuranceDesignService.register();

		//아니요 누르면 임시저장
		 boolean a2 = insuranceDesignService.saveTempInsurance();
		
		 //만약 false 그럼 담당자 연락하세요 띄우세요.
	}
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String menu() {
		return "menu";
	}
	@RequestMapping(value = "design", method = RequestMethod.GET)
	public String design() {
		return "design";
	}
//	@RequestMapping(value = "design2", method = RequestMethod.GET)
//	public String design2() {
//		return "design2";
//	}
}
