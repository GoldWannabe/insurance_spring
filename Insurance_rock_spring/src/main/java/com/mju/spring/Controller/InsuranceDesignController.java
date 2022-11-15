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
		this.insuranceDTO = insuranceDesignService.checkName(request); //중복확인 이때 체크하면서 보험 정볻 다 DTO에 셋해줘. 요율도 다 보내줌 
	
		//null이면 중복
		if(this.insuranceDTO == null) {
			model.addAttribute("OverlapError", true);//"기존의 보험이름과 중복 됩니다. 다른 이름을 정해주세요."
			
			
		}else {
//		보험이름,특약,가입조건,보상조건,설명
			this.insuranceVO.setInsuranceName(this.insuranceDTO.getInsuranceName());
			this.insuranceVO.setSpecialContract(this.insuranceDTO.getSpecialContract());
			this.insuranceVO.setApplyCondition(this.insuranceDTO.getApplyCondition());
			this.insuranceVO.setCompensateCondition(this.insuranceDTO.getCompensateCondition());
			this.insuranceVO.setExplanation(this.insuranceDTO.getExplanation());
			this.insuranceVO.setPremiumRate(this.insuranceDTO.getPremiumRate());//배열로 넣어서 안되면 따로따로 넣어야 하니까 말해줘.to은서
			
			model.addAttribute("InsuranceName", this.insuranceVO.getInsuranceName());
			model.addAttribute("SpecialContract", this.insuranceVO.getSpecialContract());
			model.addAttribute("ApplyCondition",this.insuranceVO.getApplyCondition());
			model.addAttribute("CompensateCondition",this.insuranceVO.getCompensateCondition());
			model.addAttribute("Explanation",this.insuranceVO.getExplanation());	
			model.addAttribute("PremiumRate", this.insuranceVO.getPremiumRate());
		}
		
		return "design3";
	}
	@RequestMapping(value = "popup", method = RequestMethod.GET)
	public String StandardFeePopupDesign(HttpServletRequest request, Model model) {
		this.insuranceDTO = this.insuranceDesignService.getStandardFee(request);//예를 true로 받아서 기존 보험료 주기. 아니요 일시 null
		//취소 값 생각하기.
		//premiumRate
		if(this.insuranceDTO == null ) {
			//직접 적은 등급별 요율로 계산한 기준보험료.
			this.insuranceDTO = this.insuranceDesignService.checkRate(request);
			model.addAttribute("StandardFee", this.insuranceDTO.getStandardFee());			
		}else {
			//기준보험료 계산에 실패
			model.addAttribute("StandardFee", true);//
		}
		
		return "popup";
	}
	
	@RequestMapping(value = "popup2", method = RequestMethod.GET)
	public String rateByGrade(HttpServletRequest request, Model model) {
		//요율 배열로.
		//기존 요율로 계산한 기준보험료.
		this.insuranceDTO = this.insuranceDesignService.getStandardFee(request);//직접 적은 요율을 통해 기준보험료 계산.
		this.insuranceVO.setStandardFee(this.insuranceDTO.getStandardFee());
		model.addAttribute("StandardFee", this.insuranceDTO.getStandardFee());		
		
		return "popup2";
	}
	
	@RequestMapping(value = "result", method = RequestMethod.GET)
	public void register(HttpServletRequest request, Model model) {
//		예 누르면 등록
		boolean result = insuranceDesignService.register();
		if(result) {//성공하면 true
			model.addAttribute("Result", true);//"보험 등록이 완료되었습니다"
		}else {//실패하면 false
			model.addAttribute("Result", false);//"등록에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다"
			
		}
		//취소 or 아니요 누르면 임시저장
		boolean temp = insuranceDesignService.saveTempInsurance();
		if(temp) { //임시저장에 성공하면 true
			model.addAttribute("Temp", true); //"임시저장하였습니다."
		}else {//임시저장에 실패하면 false
			model.addAttribute("Temp", false); //나중에 다시 들어왔을때 Temp로 임시저장있는지 판단. 있다면 바로 등록페이지로  이동.
		}
	}
	
//	public void temp(HttpServletRequest request) {
		//제일 시작부분
		//임시 저장한 파일이 있는지 확인
//		this.insuranceDTO = insuranceDesignService.getTempInsurance(request);
		
//		this.insuranceDTO = insuranceDesignService.checkName();
		//DTO로 받는다 요율까지
		//실패하면 다시
		
		//view 기본 요율 사용 여부 확인 
		//예 누르면 기본 요율 계산후 최종 등록할건지 말건지. getStandardFee
		//아니오 누르면 직접 입력
//		this.insuranceDTO = this.insuranceDesignService.checkRate(request);
		//실패하면 다시
		//성공하면 최종 등록
		
		
		
//		//예 누르면 등록
//		 boolean a1 = insuranceDesignService.register();
//
//		//아니요 누르면 임시저장
//		 boolean a2 = insuranceDesignService.saveTempInsurance();
		
		 //만약 false 그럼 담당자 연락하세요 띄우세요.
//	}
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String menu() {
		return "menu";
	}
	@RequestMapping(value = "design", method = RequestMethod.GET)
	public String design() {
		return "design";
	}

}
