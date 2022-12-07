package com.mju.spring.service.contractTeam;

import com.mju.spring.dao.InsuranceDao;
import com.mju.spring.dao.RegisterGeneralRateDao;
import com.mju.spring.dao.RegisterHouseRateDao;
import com.mju.spring.dao.RegisterInsuranceDao;
import com.mju.spring.dto.contractTeam.insuranceDesign.InsuranceTypeAndTermDto;
import com.mju.spring.entity.GeneralInsurance;
import com.mju.spring.entity.HouseInsurance;
import com.mju.spring.entity.Insurance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class InsuranceDesignServiceImpl implements InsuranceDesignService {

	private Insurance insurance;

	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	InsuranceDao insuranceDAO;
	@Autowired
	RegisterInsuranceDao registerInsuranceDao;
	@Autowired
	RegisterGeneralRateDao registerGeneralRateDao;
	@Autowired
	RegisterHouseRateDao registerHouseRateDao;

	 public InsuranceTypeAndTermDto getinsuranceTypeAndTerm(HttpServletRequest request) {
	      // 보험 new DTO 정보 추가
	      // 리턴 DTO
	      InsuranceTypeAndTermDto insuranceTypeAndTermDto = new InsuranceTypeAndTermDto();
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
	      
	      insuranceTypeAndTermDto.setInsuranceType(this.insurance.getInsuranceType());
	      insuranceTypeAndTermDto.setLongTerm(this.insurance.isLongTerm());

	      
	      return insuranceTypeAndTermDto;
	   }

	@Override
	public Insurance checkName(HttpServletRequest request) {
		// DTO로 받는다. 기본요율까지 +보험이름,특약,가입조건,보상조건,설명도 받아서 DTO에 Set해줘
		// 중복이 된 것이 있으면 다시this.

		if ((this.insuranceDAO.retriveName(request.getParameter("insuranceName")) != null)
				|| (registerInsuranceDao.retriveName(request.getParameter("insuranceName")) != null)) {
			return null;
		} else {
			// 이름과 기본 요율을 포함한 다양한 것들 추가
			this.insurance.setInsuranceName(request.getParameter("insuranceName"));
			this.insurance.setSpecialContract(request.getParameter("specialContract"));
			this.insurance.setApplyCondition(request.getParameter("applyCondition"));
			this.insurance.setCompensateCondition(request.getParameter("compensateCondition"));
			this.insurance.setExplanation(request.getParameter("explanation"));

			return this.insurance;
//			return null;
		}

	}

	@Override
	public Insurance getStandardFee() {
		// 기존 요율별로 기준보험료 측정된거 DTO에 set
		this.insurance.setStandardFee((int) (1000000000 * this.insurance.getPremiumRate()[0] / 100));
		return this.insurance;
//		return null;
	}

	@Override
	public Insurance checkRate(HttpServletRequest request) {
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
			//this.insuranceDTO.setPremiumRate(this.insurance.getPremiumRate());
			return getStandardFee();
		}

	}

	@Override
	public boolean register() {
		if (this.registerInsuranceDao.create(this.insurance) == 1) {
			this.registerInsuranceDao.commit();
		} else {
			return false;
		}

		if (this.insurance.getInsuranceType().toString().equals("general")) {
			return registerGeneral();
		} else if (this.insurance.getInsuranceType().toString().equals("house")) {
			return registerHouse();
		} else {
			return false;
		}

	}

	private boolean registerGeneral() {
		if (this.registerGeneralRateDao.create(this.insurance) == 3) {
			this.registerGeneralRateDao.commit();
			return true;
		} else {
			return false;
		}
	}

	private boolean registerHouse() {
		if (this.registerHouseRateDao.create(this.insurance) == 3) {
			this.registerHouseRateDao.commit();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveTempInsurance() {
		try {
			Resource resourceTempInsurance = resourceLoader.getResource("classpath:File//tempInsurance.txt");
			String tempInsurance = resourceTempInsurance.getURI().getPath();
			
			File file = new File(tempInsurance);
			FileWriter fileWriter = new FileWriter(file);
			double[] tempRate = this.insurance.getPremiumRate();
			fileWriter.write("1" + "\n" + this.insurance.getInsuranceID() + "\n" + this.insurance.getInsuranceName()
					+ "\n" + this.insurance.getInsuranceType().toString() + "\n" + this.insurance.getStandardFee()
					+ "\n" + this.insurance.getSpecialContract() + "\n" + this.insurance.isLongTerm() + "\n"
					+ this.insurance.getApplyCondition() + "\n" + this.insurance.getCompensateCondition() + "\n"
					+ this.insurance.getExplanation() + "\n" + tempRate[0] + "\n" + tempRate[1] + "\n" + tempRate[2]
					+ "\n");
			fileWriter.flush();
			fileWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Insurance getTempInsurance(HttpServletRequest request) {

		
		try {
			Resource resourceTempInsurance = resourceLoader.getResource("classpath:File//tempInsurance.txt");
			String tempInsurance = resourceTempInsurance.getURI().getPath();
			File file = new File(tempInsurance);

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

				return this.insurance;
//				return null;
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
