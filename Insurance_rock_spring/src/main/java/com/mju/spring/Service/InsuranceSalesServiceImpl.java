package com.mju.spring.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.DAO.GeneralRateDao;
import com.mju.spring.DAO.HouseRateDao;
import com.mju.spring.DAO.InsuranceDAO;
import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Entity.GeneralInsurance;
import com.mju.spring.Entity.HouseInsurance;
import com.mju.spring.Entity.Insurance;
import com.mju.spring.Entity.Insurance.EInsurance;

@Service
public class InsuranceSalesServiceImpl implements InsuranceSalesService {

	private Insurance insurance;
	private InsuranceDTO insuranceDTO;

	@Autowired
	InsuranceDAO insuranceDAO;

	@Autowired
	HouseRateDao houseRateDAO;

	@Autowired
	GeneralRateDao generalRateDAO;

	@Override
	public List<InsuranceDTO> getInsuranceList(HttpServletRequest request) {
		List<Insurance> insuranceList = null;
		if (request.getParameter("insuranceType").equals(Insurance.EInsurance.general.toString())) {
			this.insurance = new GeneralInsurance();
			this.insurance.setInsuranceType(EInsurance.general.toString());
			insuranceList = this.insuranceDAO.retriveGeneralInsuranceList(request.getParameter("insuranceType"));

		} else if (request.getParameter("insuranceType").equals(Insurance.EInsurance.house.toString())) {
			this.insurance = new HouseInsurance();
			this.insurance.setInsuranceType(EInsurance.house.toString());
			insuranceList = this.insuranceDAO.retriveHouseInsuranceList(request.getParameter("insuranceType"));
		}

		this.insuranceDTO = new InsuranceDTO();
		this.insuranceDTO.setInsuranceType(this.insurance.getInsuranceType().toString());

		List<InsuranceDTO> insuranceDTOList = new ArrayList<InsuranceDTO>();

		for (Insurance insurance : insuranceList) {
			InsuranceDTO insuranceDTO = new InsuranceDTO();
			insuranceDTO.setInsuranceName(insurance.getInsuranceName());
			insuranceDTO.setInsuranceType(insurance.getInsuranceType().toString());
			insuranceDTOList.add(insuranceDTO);
		}

		return insuranceDTOList;
	}

	@Override
	public InsuranceDTO getInsurance(HttpServletRequest request) {
		if (this.insurance.getInsuranceType().equals(EInsurance.general)) {
			this.insurance = this.insuranceDAO.retriveGeneralName(request.getParameter("insuranceName"));

			List<Double> rateList = this.generalRateDAO.retriveGeneralRate(this.insurance.getInsuranceID());
			double[] generalRate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
			this.insurance.setPremiumRate(generalRate);

		} else if (this.insurance.getInsuranceType().equals(EInsurance.house)) {
			this.insurance = this.insuranceDAO.retriveHouseName(request.getParameter("insuranceName"));

			List<Double> rateList = this.houseRateDAO.retriveHouseName(this.insurance.getInsuranceID());
			double[] generalRate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
			this.insurance.setPremiumRate(generalRate);

		}

		this.insuranceDTO.setInsuranceName(this.insurance.getInsuranceName());
		this.insuranceDTO.setLongTerm(this.insurance.isLongTerm());
		this.insuranceDTO.setApplyCondition(this.insurance.getApplyCondition());
		this.insuranceDTO.setCompensateCondition(this.insurance.getCompensateCondition());
		this.insuranceDTO.setPremiumRate(this.insurance.getPremiumRate());
		this.insuranceDTO.setExplanation(this.insurance.getExplanation());
		this.insuranceDTO.setStandardFee(this.insurance.getStandardFee());
		this.insuranceDTO.setSpecialContract(this.insurance.getSpecialContract());

		return this.insuranceDTO;
	}

	@Override
	public boolean createContract(HttpServletRequest request) {

		return false;
	}

	@Override
	public boolean createCustomer(HttpServletRequest request) {
		return false;
	}

	@Override
	public boolean joinContractAndCustomer() {
		return false;
	}

}
