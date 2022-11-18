package com.mju.spring.Service;

import com.mju.spring.DAO.InsuranceDAO;
import com.mju.spring.DAO.RegisterGeneralRateDao;
import com.mju.spring.DAO.RegisterHouseRateDao;
import com.mju.spring.DAO.RegisterInsuranceDao;
import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Entity.GeneralInsurance;
import com.mju.spring.Entity.HouseInsurance;
import com.mju.spring.Entity.Insurance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceDesignServiceImpl implements InsuranceDesignService {

	private Insurance insurance;
	private InsuranceDTO insuranceDTO;

	@Autowired InsuranceDAO insuranceDAO;
	@Autowired RegisterInsuranceDao registerInsuranceDao;
	@Autowired RegisterGeneralRateDao registerGeneralRateDao;
	@Autowired RegisterHouseRateDao registerHouseRateDao;


	@Override
	public InsuranceDTO getinsuranceTypeAndTerm(HttpServletRequest request) {
		// 보험 new DTO 정보 추가
		// 리턴 DTO

		if (request.getParameter("InsuranceType").equals(Insurance.EInsurance.general.toString())) {
			this.insurance = new GeneralInsurance();

		} else if (request.getParameter("InsuranceType").equals(Insurance.EInsurance.house.toString())) {
			this.insurance = new HouseInsurance();
		} else {
			return null;
		}

		this.insurance.setInsuranceID(UUID.randomUUID().toString());
		this.insurance.setInsuranceType(request.getParameter("InsuranceType"));
		this.insurance.setLongTerm(Boolean.parseBoolean(request.getParameter("longTerm")));

		this.insuranceDTO = new InsuranceDTO();
		this.insuranceDTO.setLongTerm(this.insurance.isLongTerm());
		this.insuranceDTO.setInsuranceType(this.insurance.getInsuranceType().toString());

		return this.insuranceDTO;
	}

	@Override
	public InsuranceDTO checkName(HttpServletRequest request) {
		// DTO로 받는다. 기본요율까지 +보험이름,특약,가입조건,보상조건,설명도 받아서 DTO에 Set해줘
		// 중복이 된 것이 있으면 다시this.

		if ((this.insuranceDAO.retriveName(request.getParameter("inusranceName")) != null)
				|| (registerInsuranceDao.retriveName(request.getParameter("inusranceName")) != null)) {
			return null;
		} else {
			// 이름과 기본 요율을 포함한 다양한 것들 추가
			this.insurance.setInsuranceName(request.getParameter("inusranceName"));
			this.insurance.setSpecialContract(request.getParameter("specialContract"));
			this.insurance.setApplyCondition(request.getParameter("applyCondition"));
			this.insurance.setCompensateCondition(request.getParameter("compensateCondition"));
			this.insurance.setExplanation(request.getParameter("explanation"));

			this.insuranceDTO.setInsuranceName(this.insurance.getInsuranceName());
			this.insuranceDTO.setSpecialContract(this.insurance.getSpecialContract());
			this.insuranceDTO.setApplyCondition(this.insurance.getApplyCondition());
			this.insuranceDTO.setCompensateCondition(this.insurance.getCompensateCondition());
			this.insuranceDTO.setExplanation(this.insurance.getExplanation());
			this.insuranceDTO.setPremiumRate(this.insurance.getPremiumRate());

			return this.insuranceDTO;
		}

	}

	@Override
	public InsuranceDTO getStandardFee() {
		// 기존 요율별로 기준보험료 측정된거 DTO에 set

		this.insurance.setStandardFee((int) (1000000000 * this.insurance.getPremiumRate()[0] / 100));
		this.insuranceDTO.setStandardFee(this.insurance.getStandardFee());
		return this.insuranceDTO;
	}

	@Override
	public InsuranceDTO checkRate(HttpServletRequest request) {
		// 요율 체크
		boolean wringRateFlag = false;

		double rate[] = new double[] { Double.parseDouble(request.getParameter("rate1")),
				Double.parseDouble(request.getParameter("rate2")), Double.parseDouble(request.getParameter("rate3")) };
		if (rate[0] > rate[1]) {
			wringRateFlag = true;
		} else if (rate[1] > rate[2]) {
			wringRateFlag = true;
		}

		if (wringRateFlag) {
			return null;
		} else {
			this.insurance.setPremiumRate(rate);
			this.insuranceDTO.setPremiumRate(this.insurance.getPremiumRate());
			return getStandardFee();
		}

	}

	@Override
	public boolean register() {
		boolean registerFlag = true;
		int result = 0;
		result = this.registerInsuranceDao.create(this.insurance);
		if (result != 1) {
			registerFlag = false;
		}
		
		result = 0;
		if (this.insurance.getInsuranceType().toString().equals("general")) {
			result = this.registerGeneralRateDao.create(this.insurance);
		} else if (this.insurance.getInsuranceType().toString().equals("house")) {
			result = this.registerHouseRateDao.create(this.insurance);
		}

		if (result != 3) {
			registerFlag = false;
		}

		System.out.println("943827326839405-");
		return registerFlag;

	}

	@Override
	public boolean saveTempInsurance() {
		try {
			File file = new File(".//File//tempInsurance.txt");
			FileWriter fileWriter = new FileWriter(file);
			double[] tempRate = this.insurance.getPremiumRate();
			fileWriter.write("1" + "\n" + this.insurance.getInsuranceID() + "\n" + this.insurance.getInsuranceName() + "\n" + this.insurance.getInsuranceType().toString()
					+ "\n" + this.insurance.getStandardFee() + "\n" + this.insurance.getSpecialContract() + "\n" + this.insurance.isLongTerm() + "\n"
					+ this.insurance.getApplyCondition() + "\n" + this.insurance.getCompensateCondition() + "\n" + this.insurance.getExplanation() + "\n"
					+ tempRate[0] + "\n" + tempRate[1] + "\n" + tempRate[2] + "\n");
			fileWriter.flush();
			fileWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public InsuranceDTO getTempInsurance(HttpServletRequest request) {

		File file = new File(".//File//tempInsurance.txt");
		try {
			@SuppressWarnings("resource")
			Scanner fileScanner = new Scanner(file);

			if (fileScanner.nextInt() == 1) {
				double rate[] = new double[3];
				String id = fileScanner.next();
				String name = fileScanner.next();
				String type = fileScanner.next();
				if (type.equals("general")) {
					this.insurance = new GeneralInsurance();
				} else if (type.equals("house")) {
					this.insurance = new HouseInsurance();
				}
				this.insurance.setInsuranceType(type);
				this.insurance.setInsuranceID(id);
				this.insurance.setInsuranceName(name);
				this.insurance.setStandardFee(fileScanner.nextInt());
				fileScanner.nextLine();
				this.insurance.setSpecialContract(fileScanner.nextLine());
				this.insurance.setLongTerm(Boolean.parseBoolean(fileScanner.nextLine()));
				this.insurance.setApplyCondition(fileScanner.nextLine());
				this.insurance.setCompensateCondition(fileScanner.nextLine());
				this.insurance.setExplanation(fileScanner.nextLine());

				rate[0] = fileScanner.nextDouble();
				rate[1] = fileScanner.nextDouble();
				rate[2] = fileScanner.nextDouble();
				this.insurance.setPremiumRate(rate);
				@SuppressWarnings("resource")
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write("0");
				fileWriter.flush();
				
				this.insuranceDTO.setInsuranceType(this.insurance.getInsuranceType().toString());
				this.insuranceDTO.setInsuranceName(this.insurance.getInsuranceName());
				this.insuranceDTO.setSpecialContract(this.insurance.getSpecialContract());
				this.insuranceDTO.setStandardFee(this.insurance.getStandardFee());
				this.insuranceDTO.setLongTerm(this.insurance.isLongTerm());
				this.insuranceDTO.setApplyCondition(this.insurance.getApplyCondition());
				this.insuranceDTO.setCompensateCondition(this.insurance.getCompensateCondition());
				this.insuranceDTO.setExplanation(this.insurance.getExplanation());
				this.insuranceDTO.setPremiumRate(this.insurance.getPremiumRate());
				
				return this.insuranceDTO;
			} else {
				return null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
