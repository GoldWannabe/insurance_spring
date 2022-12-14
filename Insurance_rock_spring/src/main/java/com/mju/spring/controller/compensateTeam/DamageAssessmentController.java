package com.mju.spring.controller.compensateTeam;

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

import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Provision;
import com.mju.spring.exception.NonExistAccidentException;
import com.mju.spring.exception.NonExistContractException;
import com.mju.spring.exception.NotDamageAssessmentException;
import com.mju.spring.exception.AlreadyPayCompletedException;
import com.mju.spring.exception.LackInsuranceBankException;
import com.mju.spring.exception.FileAcceptException;
import com.mju.spring.service.compensateTeam.DamageAssessmentService;

@Controller
public class DamageAssessmentController {

	@Autowired
	DamageAssessmentService damageAssessmentService;

	@RequestMapping(value = "selectAccidentReportMenu", method = RequestMethod.GET)
	public String selectAccidentReport(HttpServletRequest request, Model model) {
		if (request.getParameter("menu").equals("search")) {
			return "compensateTeam//damageAssessment//inputCustomerNameAndDate";
		} else if (request.getParameter("menu").equals("add")) {
			return "compensateTeam//damageAssessment//inputCustomerNameAndNum";
		} else if (request.getParameter("menu").equals("cancel")) {
			return "menu//menu";
		} else {
			return "menu//error";
		}
	}

	// 추가
	// inputNameAndPhoneNum
	// 뷰에서 이름과 폰번호 입력 후 서비스에서 계약들 리턴 / customerID
	@RequestMapping(value = "inputCustomerNameAndNum", method = RequestMethod.GET)
	public String inputNameAndPhoneNum(HttpServletRequest request, Model model){
		List<Contract> contractList = this.damageAssessmentService.addcheck(request);
		if(!contractList.isEmpty()) {
			model.addAttribute("ContractList", contractList);
			return "compensateTeam//damageAssessment//selectAddContract";			
		}else {
			//E2. 고객에 해당하는 계약이 존재하지 않는 경우
			throw new NonExistContractException();
		}

	}

	// selectAddContract	
	// 뷰에서 계약을 선택하고 서비스에서는 해당 엔티티 셋팅
	@RequestMapping(value = "selectAddContract", method = RequestMethod.GET)
	public String selectAddContract(HttpServletRequest request, Model model) {
		this.damageAssessmentService.setSelectContract(request);
		return "compensateTeam//damageAssessment//inputAccidentInfo";
	}

	// inputAccidentInfo
	// 사고 접수 버튼을 클릭하면 사고 접수.
	@RequestMapping(value = "inputAccidentInfo", method = RequestMethod.GET)
	public String inputAccidentInfo(HttpServletRequest request, Model model) {
		Accident accident = this.damageAssessmentService.addAccident(request);
		if(accident != null) {
			model.addAttribute("Accident", accident);
			return "compensateTeam//damageAssessment//checkAccidentInfo";
		}else {
			throw new NotDamageAssessmentException();
		}
	}
	
	@RequestMapping(value = "checkAccidentInfo", method = RequestMethod.GET)
	public String checkAccidentInfo(HttpServletRequest request, Model model) {
		return "menu//menu";
	}

	
	// 검색
	// inputNameAndDate
	// 뷰에서 이름과 사고 날짜 입력 서비스에서는 해당하는 것들 찾아옴
	@RequestMapping(value = "inputCustomerNameAndDate", method = RequestMethod.GET)
	public String inputCustomerNameAndDate(HttpServletRequest request, Model model) {
		List<Accident> selectAccidentList = this.damageAssessmentService.searchAccident(request);
		if(!selectAccidentList.isEmpty()) {
			model.addAttribute("AccidentList", selectAccidentList);
			return "compensateTeam//damageAssessment//selectAccident";			
		}else {
			//E5. 검색된 결과가 없는 경우
			throw new NonExistAccidentException();
			
		}
	}
	
	// 뷰에서 사고들 중 보상할 것 선택하고 서비스에서느 해당하는 것 선택 후 고객 정보와 사고 정보 리턴
	@RequestMapping(value = "selectAccident", method = RequestMethod.GET)
	public String selectAccident(HttpServletRequest request, Model model) {
		//가입자명, 연락처, 사고번호의 사고의 책임비용원을 지급하시겠습니까?
		//보상급 지급 여부와 책임비용을  요청한다.
		String[] array = request.getParameter("select").split(" ");
		if(array[0].equals("compensation")) {
			Accident accident = this.damageAssessmentService.getSelectAccident(request);
//			accdent가 null인 경우는 앞에서 한번 걸렀으므로 완전한 오류
			model.addAttribute("CustomerName", accident.getCustomerName());
			model.addAttribute("CustomerPhoneNum", accident.getCustomerPhoneNum());
			model.addAttribute("Content", accident.getContent());
			model.addAttribute("LiablityCost", accident.getLiablityCost());
			return "compensateTeam//compensation//selectCompensation";
			
		}else if(array[0].equals("modification")) {
			Accident accident = this.damageAssessmentService.getSelectAccident(request);
			model.addAttribute("AccidentID", accident.getAccidentID());
			model.addAttribute("CustomerName", accident.getCustomerName());
			model.addAttribute("CustomerPhoneNum", accident.getCustomerPhoneNum());
			model.addAttribute("PayCompleted", accident.isPayCompleted());
			model.addAttribute("ContractID", accident.getContractID());

			model.addAttribute("AccidentDate", accident.getAccidentDate());
			model.addAttribute("Content", accident.getContent());
			model.addAttribute("KindOfCost", accident.getKindOfCost());
			model.addAttribute("DamagePer", accident.getDamagePer());
			model.addAttribute("TotalCost", accident.getTotalCost());
			model.addAttribute("LiablityCost", accident.getLiablityCost());
			model.addAttribute("LiablityRate", accident.getLiablityRate());
			
			return "compensateTeam//damageAssessment//selectModification";
		}else {
			return "menu//error";
		}
		
	}
	@SuppressWarnings("unused")
	@RequestMapping(value = "selectCompensation", method = RequestMethod.GET)
	public String selectCompensation(HttpServletRequest request, Model model) {
		if(request.getParameter("select").equals("compensation")) {
			Provision provision = this.damageAssessmentService.payCompensation();
			if(provision.getBankName().equals("잔고부족")) {
				throw new LackInsuranceBankException();
				
			}else if (provision.getBankName().equals("통장문제")) {
				throw new FileAcceptException();
				
			}else if (provision.getBankName().equals("이미보상완료")) {
				throw new AlreadyPayCompletedException();
				
			}else  if(provision != null) {
				model.addAttribute("JudgeResult", "보험금지급이 완료되었습니다.");
				return "menu//showResult";
			}else {
				return "menu//error";
			}
		}else if(request.getParameter("select").equals("cancel")) {
			return "menu//menu";
		}else {
			return "menu//error";
		}
		
	}
	@RequestMapping(value = "selectModification", method = RequestMethod.GET)
	public String selectModification(HttpServletRequest request, Model model) {
		//수정된 내용들을 받아서 업데이트
		boolean checkModification = this.damageAssessmentService.modify(request);
		if(checkModification) {
			model.addAttribute("JudgeResult", "사고정보가 변경되었습니다.");
			return "menu//showResult";
		}else {
			return "menu//error";
		}
	}
	/////(공통)DB접근 실패///////////
//	E3. DB 접근에 실패한 경우 E2. DB 접근에 실패한 경우
	@ExceptionHandler(PersistenceException.class)
	private ModelAndView persistenceException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", "DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		return modelAndView;
	}
	
	////////////////////////손해사정을 하다 에러처리/////////////////////////////////////
//	E2.고객에 해당하는 계약이 존재하지 않는 경우
	@ExceptionHandler(NonExistContractException.class)
	private ModelAndView nonExistContractException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("compensateTeam/damageAssessment/selectAddContract");
		modelAndView.addObject("NotContract", "해당 고객이 가입한 보험이 존재하지 않습니다. 다시 입력해주세요.");
		return modelAndView;
	}
//	E4.검색된 결과가 없는 경우
	@ExceptionHandler(NonExistAccidentException.class)
	private ModelAndView nonExistAccidentException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("compensateTeam/damageAssessment/selectAccident");
		modelAndView.addObject("NotAccident", "사고 정보를 찾지 못했습니다. 사고날짜와 가입자명을 오탈자 없이 적어주세요.");
		return modelAndView;
	}
//	E5.손해사정이 이루어지지 않은 경우
	@ExceptionHandler(NotDamageAssessmentException.class)
	private ModelAndView notDamageAssessmentException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", "손해사정이 완료 되지않았습니다.");
		return modelAndView;
	}
	////////////보상금을 지급하다 에러 처리////////////////////////////////
//	E1.이미 보상금을 지급한 경우
	@ExceptionHandler(AlreadyPayCompletedException.class)
	private ModelAndView alreadyPayCompletedException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", "이미 보상금이 지급 완료되었습니다.");
		return modelAndView;
	}
	//E3.잔액이 부족한 경우
	@ExceptionHandler(LackInsuranceBankException.class)
	private ModelAndView lackInsuranceBankException(Exception e) {
		System.err.println(e.getMessage());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("menu//showResult");
		modelAndView.addObject("JudgeResult", "보험 통장의 잔액이 부족합니다. 확인후 다시 진행해 주세요");
		return modelAndView;
	}
	
//	E4.파일 업데이트에 실패한 경우
		@ExceptionHandler(FileAcceptException.class)
		private ModelAndView fileAcceptException(Exception e) {
			System.err.println(e.getMessage());
			ModelAndView modelAndView= new ModelAndView();
			modelAndView.setViewName("menu//showResult");
			modelAndView.addObject("JudgeResult", "통장에 문제가 생겼습니다. 관련 팀(1234-5678)에 최대한 빠르게 연락바랍니다.");
			return modelAndView;
		}
		

}
