package com.mju.spring.service.contractTeam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ApplyContractDao;
import com.mju.spring.dao.CustomerDao;
import com.mju.spring.dao.CustomerRankDao;
import com.mju.spring.dao.GeneralRateDao;
import com.mju.spring.dao.HouseRateDao;
import com.mju.spring.dao.InsuranceDao;
import com.mju.spring.dao.RankDao;
import com.mju.spring.dao.RenewContractDao;
import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.ReasonDto;
import com.mju.spring.dto.contractTeam.Underwriting.RenewContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyApplyContractDto;
import com.mju.spring.dto.contractTeam.Underwriting.VerifyRenewContractDto;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Customer;
import com.mju.spring.entity.Insurance;
import com.mju.spring.entity.Rank;

@Service
public class UnderwritingServiceImpl implements UnderwritingService {

	@Autowired
	ApplyContractDao applyContractDao;
	@Autowired
	RenewContractDao renewContractDao;
	@Autowired
	InsuranceDao insuranceDao;
	@Autowired
	GeneralRateDao generalRateDao;
	@Autowired
	HouseRateDao houseRateDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	CustomerRankDao customerRankDao;
	@Autowired
	RankDao rankDao;

	private Contract contract;
	private Insurance insurance;
	private Customer customer;
	private Rank rank;
	private List<ApplyContractDto> applyContractDtoList;
	private List<RenewContractDto> renewContractDtoList;

	@Override
	public List<ApplyContractDto> getApply() {
		this.applyContractDtoList = this.applyContractDao.retriveApplyContractList();
		return this.applyContractDtoList;

	}

	@Override
	public List<RenewContractDto> getRenew() {
		this.renewContractDtoList = this.renewContractDao.retriveRenewContractList();
		return this.renewContractDtoList;
	}

	@Override
	public VerifyApplyContractDto verifyApply(HttpServletRequest request) {
		setApplyToContract(Integer.parseInt(request.getParameter("index")));
		if (!getInsurance() || !getCustomer()) {
			return null;
		}

//		if (!verifyPeriod() || !verifyPremium()) {
//			return true;
//		}

		return null;
	}

	private void setApplyToContract(int index) {
		this.contract = new Contract();
		ApplyContractDto applyContractDto = this.applyContractDtoList.get(index);
		this.contract.setContractID(applyContractDto.getContractID());
		this.contract.setCustomerID(applyContractDto.getCustomerID());
		this.contract.setCustomerName(applyContractDto.getCustomerName());
		this.contract.setCustomerPhoneNum(applyContractDto.getCustomerPhoneNum());
		this.contract.setInsuranceID(applyContractDto.getInsuranceID());
		this.contract.setInsuranceName(applyContractDto.getInsuranceName());
		this.contract.setPaymentCycle(applyContractDto.getPaymentCycle());
		this.contract.setInsuranceFee(applyContractDto.getInsuranceFee());
		this.contract.setSecurityFee(applyContractDto.getSecurityFee());
		this.contract.setPeriod(applyContractDto.getPeriod());
	}

	private boolean getInsurance() {
		String type = this.insuranceDao.retriveInsuranceType(this.contract.getInsuranceID());
		if (type.equals("general")) {
			this.insurance = this.insuranceDao.retriveGeneralById(this.contract.getInsuranceID());
			List<Double> rateList = this.generalRateDao.retriveGeneralRate(this.contract.getInsuranceID());
			double[] rate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
			this.insurance.setPremiumRate(rate);
			return true;
		} else if (type.equals("house")) {
			this.insurance = this.insuranceDao.retriveHouseById(this.contract.getInsuranceID());
			List<Double> rateList = this.houseRateDao.retriveHouseName(this.contract.getInsuranceID());
			double[] rate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
			this.insurance.setPremiumRate(rate);
			return true;
		} else {
			return false;
		}
	}

	private boolean getCustomer() {
//		this.customer = this.customerDao.retriveCustomerById(this.contract.getCustomerID());
//		ArrayList<String> rankIDList = new ArrayList<String>();
//		rankIDList.add(this.customerRankDao.retriveRankID(this.contract.getContractID()));
//		this.customer.setRankID(rankIDList);
		// this.customer.setRank(this.rankDao.retriveRankById());

		return false;
	}

	@Override
	public VerifyRenewContractDto verifyRenew() {
		return null;
	}

	@Override
	public ReasonDto getReason() {
		ReasonDto reasonDto = new ReasonDto();
		reasonDto.setReason(this.contract.getReason());
		return reasonDto;
	}

	@Override
	public boolean permitApply() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notPermitApply() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean permitRenew() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notPermitRenew() {
		// TODO Auto-generated method stub
		return false;
	}

}
