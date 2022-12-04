package com.mju.spring.service.salesTeam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ApplyContractDao;
import com.mju.spring.dao.ApplyContractDaoImpl;
import com.mju.spring.dao.CustomerDao;
import com.mju.spring.dao.CustomerRankDao;
import com.mju.spring.dao.GeneralRateDao;
import com.mju.spring.dao.HouseRateDao;
import com.mju.spring.dao.InsuranceDao;
import com.mju.spring.dao.RankDao;
import com.mju.spring.dto.ApplyContractDto;
import com.mju.spring.dto.CustomerDto;
import com.mju.spring.dto.CustomerRankDto;
import com.mju.spring.dto.InsuranceDto;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Customer;
import com.mju.spring.entity.GeneralInsurance;
import com.mju.spring.entity.HouseInsurance;
import com.mju.spring.entity.Insurance;
import com.mju.spring.entity.Insurance.EInsurance;
import com.mju.spring.entity.Rank;

@Service
public class InsuranceSalesServiceImpl implements InsuranceSalesService {

	private Insurance insurance;
	private InsuranceDto insuranceDTO;
	private ApplyContractDto applyContractDTO;
	private CustomerDto customerDTO;
	private CustomerRankDto customerRankDTO;
	
	private Contract applyContract;
	private Customer customer;
	private Rank rank;
	

	@Autowired
	InsuranceDao insuranceDAO;

	@Autowired
	HouseRateDao houseRateDAO;

	@Autowired
	GeneralRateDao generalRateDAO;
	
	@Autowired
	ApplyContractDao applyContractDAO;
	
	@Autowired
	CustomerDao customerDAO;
	
	@Autowired
	RankDao rankDAO;
	
	@Autowired
	CustomerRankDao customerRankDAO;
	

	@Override
	public List<InsuranceDto> getInsuranceList(HttpServletRequest request) {
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

		this.insuranceDTO = new InsuranceDto();
		this.insuranceDTO.setInsuranceType(this.insurance.getInsuranceType().toString());

		List<InsuranceDto> insuranceDTOList = new ArrayList<InsuranceDto>();

		for (Insurance insurance : insuranceList) {
			InsuranceDto insuranceDTO = new InsuranceDto();
			insuranceDTO.setInsuranceName(insurance.getInsuranceName());
			insuranceDTO.setInsuranceType(insurance.getInsuranceType().toString());
			insuranceDTOList.add(insuranceDTO);
		}

		return insuranceDTOList;
	}

	@Override
	public InsuranceDto getInsurance(HttpServletRequest request) {
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
	public boolean createCustomer(HttpServletRequest request) {
		this.customer = new Customer();
		
		this.customer.setName(request.getParameter("customerName"));
		this.customer.setSSN(request.getParameter("SSN"));
		this.customer.setPhoneNum(request.getParameter("phoneNum"));
		this.customer.setAddress(request.getParameter("address"));
		this.customer.setSex(request.getParameter("gender"));
		this.customer.setBankName(request.getParameter("bankName"));
		this.customer.setAccountNum(request.getParameter("accountNum"));
		//applycontract = 0.1 (contract = 1)
		this.customer.setInsuranceNum(0.1);
		
		this.customerDTO = new CustomerDto();
		this.customerDTO.setName(this.customer.getName());
		this.customerDTO.setSSN(this.customer.getSSN());
		this.customerDTO.setPhoneNum(this.customer.getPhoneNum());
		this.customerDTO.setAddress(this.customer.getAddress());
		this.customerDTO.setSex(this.customer.getSex().toString());
		this.customerDTO.setBankName(this.customer.getBankName());
		this.customerDTO.setAccountNum(this.customer.getAccountNum());
		this.customerDTO.setInsuranceNum(this.customer.getInsuranceNum());
		
		if(this.customerDTO != null) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean createApplyContract(HttpServletRequest request) {
		this.applyContract = new Contract();
		this.rank = new Rank();
		
		this.applyContract.setCustomerID(this.customer.getCustomerID());
		this.applyContract.setCustomerName(this.customer.getName());
		this.applyContract.setCustomerPhoneNum(this.customer.getPhoneNum());
		this.applyContract.setInsuranceID(this.insurance.getInsuranceID());
		this.applyContract.setInsuranceName(this.insurance.getInsuranceName());
		this.applyContract.setSecurityFee(Integer.valueOf(request.getParameter("securityFee")));
		this.applyContract.setInsuranceFee(Integer.valueOf(request.getParameter("insuranceFee")));
		this.applyContract.setPaymentCycle(Integer.valueOf(request.getParameter("paymentCycle")));
		this.applyContract.setPeriod(Integer.valueOf(request.getParameter("period")));
		
		this.rank.setFireFacilities(Integer.valueOf(request.getParameter("fireFedilities")));
		this.rank.setScale(Integer.valueOf(request.getParameter("scale")));
		this.rank.setSurroundingFacilities(Double.valueOf(request.getParameter("surroundingFedilities")));
		this.rank.setHeight(Boolean.valueOf(request.getParameter("height")));
		this.rank.setMaterial(request.getParameter("meterial"));
		this.rank.setPurpose(request.getParameter("goal"));
		
		if(this.applyContract != null && this.rank != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean joinApplyContractAndCustomer() {
		this.applyContractDAO = new ApplyContractDaoImpl();
		String[] customerRank = new String[]{this.customer.getCustomerID(), this.applyContract.getContractID(), this.rank.getRankID()};
		
		if(this.rankDAO.create(this.rank) == 1 && this.applyContractDAO.create(this.applyContract) == 1) {
			this.applyContractDAO.commit();
		}
		return false;
	}

}
