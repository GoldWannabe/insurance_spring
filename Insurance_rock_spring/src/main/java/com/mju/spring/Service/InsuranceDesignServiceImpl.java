package com.mju.spring.Service;

import com.mju.spring.DAO.InsuranceDAO;
import com.mju.spring.DAO.RegisterInsuranceDao;
import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Entity.GeneralInsurance;
import com.mju.spring.Entity.HouseInsurance;
import com.mju.spring.Entity.Insurance;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceDesignServiceImpl implements InsuranceDesignService {

	private Insurance insurance;
	private InsuranceDTO insuranceDTO;
	
	@Autowired
	InsuranceDAO insuranceDAO;
	RegisterInsuranceDao registerInsuranceDao;
	

//	@Override
//	public InsuranceDTO getinsuranceType(EInsurance insuranceType) {
//		Scanner scanner = new Scanner(System.in);
//
//		boolean IsLongTerm = false;
//		int flag = -1;
//
//		if (flag == 1) {
//			this.insurance = new GeneralInsurance();
//			this.insurance.setLongTerm(true);
//		} else if (flag == 2) {
//			this.insurance = new HouseInsurance();
//			this.insurance.setLongTerm(true);
//		} else if (flag == 0) {
//			return false;
//		}
//
//		flag = -1;
//		while (flag == -1) {
//			this.contractTeamTui.showSelectRate(Arrays.toString(this.insurance.getPremiumRate()));
//
//			try {
//				flag = (getflag(scanner.next()));
//			} catch (WrongInputException e) {
//				System.err.println(e.getMessage());
//			}
//		}
//		if (flag == 1) {
//
//			this.insurance.setStandardFee((int) (1000000000 * this.insurance.getPremiumRate()[0] / 100));
//		} else if (flag == 2) {
//
//			double rate[] = new double[] { 0, 0, 0 };
//			boolean correctRate = false;
//			while (!correctRate) {
//				this.contractTeamTui.showEnterPremiumRate();
//				rate[0] = checkDouble(scanner);
//				rate[1] = checkDouble(scanner);
//				rate[2] = checkDouble(scanner);
//				correctRate = checkRate(rate);
//
//			}
//			this.insurance.setPremiumRate(rate);
//			this.insurance.setStandardFee((int) (1000000000 * this.insurance.getPremiumRate()[0] / 100));
//		} else if (flag == 0) {
//			this.contractTeamTui.showCancel();
//			return false;
//		}
//
//		return register(scanner);
//
//		return null;
//	}
//
//	public void setName() {
//		try {
//
//			this.insurance.setInsuranceName(scanner.next());
//			overlapName = checkName();
//
//		} catch (OverlapNameException e) {
//			System.err.println(e.getMessage());
//		}
//
//		this.insurance.setSpecialContract();
//
//		this.insurance.setApplyCondition();
//
//		this.insurance.setCompensateCondition();
//
//		this.insurance.setExplanation();
//	}
//
//	public void getStandardRate() {
//		return this.insurance.getPremiumRate();
//	}
//
//	public void setRate() {
//
//	}

	@Override
	public InsuranceDTO getinsuranceTypeAndTerm(HttpServletRequest request) {
		//보험 new DTO 정보 추가 
		//리턴 DTO
		
//		if(Boolean.parseBoolean(request.getParameter("longTerm"))) {
//			insuranceDTO.setLongTerm(true);
//		}else if(Boolean.parseBoolean(request.getParameter("longTerm"))) {//term이라는 체크박스 네임 태그에서 장기면 단기면.
//			insuranceDTO.setLongTerm(false);
//		}
		
		if(request.getParameter("type").equals(Insurance.EInsurance.general.toString())) {
			this.insurance = new GeneralInsurance();
			
		} else if(request.getParameter("type").equals(Insurance.EInsurance.house.toString())) {
			this.insurance = new HouseInsurance();
		} else {
			return null;
		}
		
		this.insurance.setInsuranceType(request.getParameter("type"));
		this.insurance.setLongTerm(Boolean.parseBoolean(request.getParameter("longTerm")));
		
		this.insuranceDTO = new InsuranceDTO();
		this.insuranceDTO.setLongTerm(this.insurance.isLongTerm());
		this.insuranceDTO.setInsuranceType(this.insurance.getInsuranceType().toString());
				
		return this.insuranceDTO;
	}

	@Override
	public InsuranceDTO checkName(HttpServletRequest request) {
		//DTO로 받는다.  기본요율까지 +보험이름,특약,가입조건,보상조건,설명도 받아서 DTO에 Set해줘
		//중복이 된 것이 있으면 다시
//		if(this.insuranceDAO.retriveName(request.getParameter("name")) != null) {
//			return null;
//		} else {
//			//이름과 기본 요율을 포함한 다양한 것들 추가
//		}
//		
		
		return this.insuranceDTO;
	}

	@Override
	public InsuranceDTO checkRate() {
		//요율 체크
//		double rate[] = new double[] { 0, 0, 0 };
//		boolean correctRate = false;
//			this.contractTeamTui.showEnterPremiumRate();
//			correctRate = checkRate(rate);
//

		return null;
	}

	@Override
	public boolean register() {
		// 등록하세요
		return false;
	}

	@Override
	public boolean saveTempInsurance() {
		// 파일 저장
		return false;
	}

	@Override
	public InsuranceDTO getTempInsurance() {
		// 파일 찾기
		return null;
	}

	@Override
	public InsuranceDTO getStandardFee(HttpServletRequest request) {
		//기존 요율별로 기준보험료 측정된거  DTO에 set
		// TODO Auto-generated method stub
		return null;
	}
}
