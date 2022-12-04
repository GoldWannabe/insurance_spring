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

		if (!verifyPeriod() || !verifyPremium()) {
			return null;
		}

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
		this.customer = this.customerDao.retriveCustomerById(this.contract.getCustomerID());
		ArrayList<String> rankIDList = new ArrayList<String>();
		rankIDList.add(this.customerRankDao.retriveRankID(this.contract.getContractID()));
		this.customer.setRankID(rankIDList);
		this.customer.setRank(this.rankDao.retriveRankById(this.customer.getRankID().get(0)));

		return true;
	}

	private boolean verifyPeriod() {
		if ((this.contract.getPeriod() >= 36) && (this.insurance.isLongTerm() != true)) {
			String reason = "장기 계약 불가능한 보험";
			this.contract.setReason(reason);
		}
		return false;
	}
	
	private boolean verifyPremium() {

		double rank = getMaterialRank();

		switch (this.customer.getRank().getPurpose()) {
		case "living":
			rank = verifyLiving(rank);
			break;
		case "factory":
			rank = verifyFactory(rank);
			break;
		case "culturalAsset":
			rank = verifyCulturalAsset(rank);
			break;
		case "store":
			rank = verifyStore(rank);
			break;
		case "office":
			rank = verifyOffice(rank);
			break;
		case "carPark":
			rank = verifyCarPark(rank);
			break;
		case "warehouse":
			rank = verifyWarehouse(rank);
			break;

		}

		return verifyInsuranceFee(rank);
	}

	private double getMaterialRank() {
		switch (this.customer.getRank().getMaterial()) {
		case "rock":
			return 1;

		case "iron":
			return 1;

		case "brick":
			return 1.5;

		case "concrete":
			return 2;

		case "wood":
			return 3;
		}
		return 0;
	}

	private double verifyLiving(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 30) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyFactory(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities2();
		totalRank = totalRank + checkSurroundingFacilities2();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 200) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getMaterial().equals("wood")) {
			totalRank = totalRank + 1.0;
		}
		return totalRank;
	}

	private double verifyCulturalAsset(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities2();
		totalRank = totalRank + checkSurroundingFacilities2();
		if (this.customer.getRank().getScale() > 60) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getMaterial().equals("wood")) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyStore(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 20) {
			totalRank = totalRank + 0.5;
		}

		return totalRank;
	}

	private double verifyOffice(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 20) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyCarPark(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().getScale() > 200) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyWarehouse(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities2();
		totalRank = totalRank + checkSurroundingFacilities2();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 200) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getMaterial().equals("wood")) {
			totalRank = totalRank + 1.0;
		}
		return totalRank;
	}

	private double checkFirefacilities1() {
		double facilities = this.customer.getRank().getFireFacilities();
		if (facilities > 4) {
			return -0.6;
		} else if (facilities > 2) {
			return -0.4;
		} else if (facilities > 1) {
			return -0.2;
		} else {
			return 0;
		}
	}

	private double checkFirefacilities2() {
		double facilities = this.customer.getRank().getFireFacilities();
		if (facilities > 4) {
			return -0.5;
		} else if (facilities > 2) {
			return -0.2;
		} else if (facilities > 1) {
			return 0.3;
		} else {
			return 0.5;
		}
	}

	private double checkSurroundingFacilities1() {
		double facilities = this.customer.getRank().getSurroundingFacilities();
		if (facilities < 2) {
			return 0.2;
		} else if (facilities < 4) {
			return 0.5;
		} else if (facilities < 5) {
			return 0.8;
		} else {
			return 1;
		}
	}

	private double checkSurroundingFacilities2() {
		double facilities = this.customer.getRank().getSurroundingFacilities();
		if (facilities < 2) {
			return 0.1;
		} else if (facilities < 4) {
			return 0.6;
		} else if (facilities < 5) {
			return 0.9;
		} else {
			return 1.1;
		}
	}

	private boolean verifyInsuranceFee(double rank) {

		double rate;
		if (rank < 1.5) {
			rate = this.insurance.getPremiumRate()[0];
		} else if (rank < 2.5) {
			rate = this.insurance.getPremiumRate()[1];
		} else {
			rate = this.insurance.getPremiumRate()[2];
		}

		int standardFee = (int) (this.contract.getSecurityFee() * rate / 100);

		if (standardFee > this.contract.getInsuranceFee()) {
			String reason = "보험료가 기준보험료 보다 적습니다.";
			return false;
		} else {

			return true;
		}
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
