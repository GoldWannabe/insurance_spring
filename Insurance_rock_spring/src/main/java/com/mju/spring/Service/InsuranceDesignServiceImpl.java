package com.mju.spring.Service;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Entity.GeneralInsurance;
import com.mju.spring.Entity.HouseInsurance;
import com.mju.spring.Entity.Insurance;

import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class InsuranceDesignServiceImpl implements InsuranceDesignService {

	private Insurance insurance;

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
		return null;
	}

	@Override
	public InsuranceDTO checkName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InsuranceDTO checkRate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveTempInsurance() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InsuranceDTO getTempInsurance() {
		// TODO Auto-generated method stub
		return null;
	}
}
