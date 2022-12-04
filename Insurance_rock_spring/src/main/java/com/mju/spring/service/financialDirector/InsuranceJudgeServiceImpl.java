package com.mju.spring.service.financialDirector;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.GeneralRateDao;
import com.mju.spring.dao.HouseRateDao;
import com.mju.spring.dao.InsuranceDao;
import com.mju.spring.dao.RegisterGeneralRateDao;
import com.mju.spring.dao.RegisterHouseRateDao;
import com.mju.spring.dao.RegisterInsuranceDao;
import com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto;
import com.mju.spring.entity.Insurance;
import com.mju.spring.entity.Insurance.EInsurance;

@Service
public class InsuranceJudgeServiceImpl implements InsuranceJudgeService {

	@Autowired
	RegisterInsuranceDao registerInsuranceDao;
	@Autowired
	RegisterHouseRateDao registerHouseRateDao;
	@Autowired
	RegisterGeneralRateDao registerGeneralRateDao;
	@Autowired
	InsuranceDao insuranceDao;
	@Autowired
	HouseRateDao houseRateDao;
	@Autowired
	GeneralRateDao generalRateDao;

	private Insurance insurance;
	private List<RegisterInsuranceDto> registerInsuranceDtoList;

	@Override
	public List<RegisterInsuranceDto> getRegisterInsurance() {
		this.registerInsuranceDtoList = this.registerInsuranceDao.retriveRegisterInsuranceList();
		return this.registerInsuranceDtoList;
	}

	@Override
	public Insurance selectJudgeInsurance(HttpServletRequest request) {
		String name = this.registerInsuranceDtoList.get(Integer.parseInt(request.getParameter("num"))).getInsuranceName();
		EInsurance type = this.registerInsuranceDtoList.get(Integer.parseInt(request.getParameter("num"))).getInsuranceType();
		if (type.equals(EInsurance.general)) {
			// 이어서 요율도 따로 받아와야함
			this.insurance = this.registerInsuranceDao.retriveGeneralName(name);
			List<Double> rateList = (this.registerGeneralRateDao.retriveById(this.insurance.getInsuranceID()));
			double[] rate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
			this.insurance.setPremiumRate(rate);
			return this.insurance;

		} else if (type.equals(EInsurance.house)) {
			this.insurance = this.registerInsuranceDao.retriveHouseName(name);
			List<Double> rateList = (this.registerHouseRateDao.retriveById(this.insurance.getInsuranceID()));
			double[] rate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
			this.insurance.setPremiumRate(rate);
			return this.insurance;

		} else {
			return null;
		}

	}

	@Override
	public boolean permit() {
		if (createInsurance()) {
			return deleteRegister();
		} else {
			return false;
		}

	}

	@Override
	public boolean notPermit() {
		return deleteRegister();
	}

	private boolean createInsurance() {
		this.insurance.setReleaseDate(LocalDate.now());
		
		if (this.insuranceDao.create(this.insurance) ==1) {
			this.insuranceDao.commit();

			if (this.insurance.getInsuranceType() == EInsurance.general) {
				return createGeneralRate();

			} else if (this.insurance.getInsuranceType() == EInsurance.house) {
				return createHouseRate();

			} else {
				return false;

			}
		} else {
			return false;
		}
	}

	private boolean createGeneralRate() {
		if (this.generalRateDao.create(this.insurance) ==3) {
			this.generalRateDao.commit();
			return true;

		} else {
			return false;
		}
	}

	private boolean createHouseRate() {
		if (this.houseRateDao.create(this.insurance) ==3) {
			this.houseRateDao.commit();
			return true;

		} else {
			return false;
		}
	}

	private boolean deleteRegister() {
		if (this.insurance.getInsuranceType() == EInsurance.general) {
			return deleteRegisterGeneralRate();
		} else if (this.insurance.getInsuranceType() == EInsurance.house) {
			return deleteRegisterHouseRate();
		} else {
			return false;
		}
	}

	private boolean deleteRegisterGeneralRate() {
		if (this.registerGeneralRateDao.delete(this.insurance.getInsuranceID())==3) {
			this.registerGeneralRateDao.commit();
			return deleteRegisterInsurance();
		} else {
			return false;
		}
	}

	

	private boolean deleteRegisterHouseRate() {
		if (this.registerHouseRateDao.delete(this.insurance.getInsuranceID())==3) {
			this.registerHouseRateDao.commit();
			return deleteRegisterInsurance();
		} else {
			return false;
		}
	}

	private boolean deleteRegisterInsurance() {
		if (this.registerInsuranceDao.delete(this.insurance.getInsuranceID())==1) {
			this.registerInsuranceDao.commit();
			return true;
		} else {
			return false;
		}
	}
}
