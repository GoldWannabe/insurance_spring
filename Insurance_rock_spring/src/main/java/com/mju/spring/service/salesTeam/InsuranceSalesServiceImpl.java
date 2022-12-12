package com.mju.spring.service.salesTeam;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ApplyContractDao;
import com.mju.spring.dao.ApplyContractDaoImpl;
import com.mju.spring.dao.CustomerDao;
import com.mju.spring.dao.CustomerDaoImpl;
import com.mju.spring.dao.CustomerRankDao;
import com.mju.spring.dao.FailContractDao;
import com.mju.spring.dao.FailContractDaoImpl;
import com.mju.spring.dao.GeneralRateDao;
import com.mju.spring.dao.HouseRateDao;
import com.mju.spring.dao.InsuranceDao;
import com.mju.spring.dao.RankDao;
import com.mju.spring.dto.salesTeam.InsuranceSales.CustomerDto;
import com.mju.spring.dto.salesTeam.InsuranceSales.CustomerRankDto;
import com.mju.spring.dto.salesTeam.InsuranceSales.FailContractDto;
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
	private CustomerRankDto customerRankDTO;
	private FailContractDto failContractDTO;
	private CustomerDto customerDTO;

	private Contract applyContract;
	private Customer customer;
	private Rank rank;
	private List<Contract> failContractList;
	private Contract selectedFailContract;

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

	@Autowired
	FailContractDao failContractDAO;

	@Override
	public List<Insurance> getInsuranceList(HttpServletRequest request) {
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

		return insuranceList;
	}

	@Override
	public Insurance getInsurance(HttpServletRequest request) {
		if (this.insurance.getInsuranceType().equals(EInsurance.general)) {
			if (this.insuranceDAO.retriveGeneralName(request.getParameter("insuranceName")) != null) {
				this.insurance = this.insuranceDAO.retriveGeneralName(request.getParameter("insuranceName"));
				List<Double> rateList = this.generalRateDAO.retriveGeneralRate(this.insurance.getInsuranceID());
				double[] generalRate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
				this.insurance.setPremiumRate(generalRate);
			} else {
				// null 일때
				return null;
			}

		} else if (this.insurance.getInsuranceType().equals(EInsurance.house)) {
			if (this.insuranceDAO.retriveHouseName(request.getParameter("insuranceName")) != null) {
				this.insurance = this.insuranceDAO.retriveHouseName(request.getParameter("insuranceName"));
				List<Double> rateList = this.houseRateDAO.retriveHouseName(this.insurance.getInsuranceID());
				double[] generalRate = new double[] { rateList.get(0), rateList.get(1), rateList.get(2) };
				this.insurance.setPremiumRate(generalRate);
			} else {
				return null;
			}
		}
		return this.insurance;
	}

	@Override
	public boolean searchCustomer(HttpServletRequest request) {
		this.customerDTO = new CustomerDto();
		this.customerDTO.setName(request.getParameter("customerName"));
		this.customerDTO.setPhoneNum(request.getParameter("phoneNum"));
		this.customer = this.customerDAO.retriveCustomerByNameAndPhoneNum(this.customerDTO);
		if (this.customer != null) {
			// 기존고객
			this.customer.setInsuranceNum(this.customer.getInsuranceNum() + (0.1));
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean createCustomer(HttpServletRequest request) {
		this.customer = new Customer();

		String customerID = UUID.randomUUID().toString().substring(0, 5);
		this.customer.setCustomerID(customerID);

		this.customer.setName(request.getParameter("customerName"));
		this.customer.setSSN(request.getParameter("SSN"));
		this.customer.setPhoneNum(request.getParameter("phoneNum"));
		this.customer.setAddress(request.getParameter("address"));
		this.customer.setSex(request.getParameter("gender"));
		this.customer.setBankName(request.getParameter("bankName"));
		this.customer.setAccountNum(request.getParameter("accountNum"));
		// applycontract = 0.1 (contract = 1)
		this.customer.setInsuranceNum(0.1);

		if (this.customer != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean createApplyContract(HttpServletRequest request) {
		this.applyContract = new Contract();
		this.rank = new Rank();

		String applyContractID = UUID.randomUUID().toString().substring(0, 5);
		this.applyContract.setContractID(applyContractID);
		this.applyContract.setCustomerID(this.customer.getCustomerID());
		this.applyContract.setCustomerName(this.customer.getName());
		this.applyContract.setCustomerPhoneNum(this.customer.getPhoneNum());
		this.applyContract.setInsuranceID(this.insurance.getInsuranceID());
		this.applyContract.setInsuranceName(this.insurance.getInsuranceName());
		this.applyContract.setSecurityFee(Integer.valueOf(request.getParameter("securityFee")));
		this.applyContract.setInsuranceFee(Integer.valueOf(request.getParameter("insuranceFee")));
		this.applyContract.setPaymentCycle(Integer.valueOf(request.getParameter("paymentCycle")));
		this.applyContract.setPeriod(Integer.valueOf(request.getParameter("period")));

		String rankID = UUID.randomUUID().toString().substring(0, 5);
		this.rank.setRankID(rankID);
		this.rank.setFireFacilities(Float.valueOf(request.getParameter("fireFedilities")));
		this.rank.setScale(Integer.valueOf(request.getParameter("scale")));
		this.rank.setSurroundingFacilities(Float.valueOf(request.getParameter("surroundingFedilities")));
		this.rank.setHeight(Boolean.valueOf(request.getParameter("height")));
		this.rank.setMaterial(request.getParameter("meterial"));
		this.rank.setPurpose(request.getParameter("goal"));

		if (this.applyContract != null && this.rank != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean joinApplyContractAndUpdateCustomer() {
		this.applyContractDAO = new ApplyContractDaoImpl();
		this.customerDAO = new CustomerDaoImpl();

		this.customerRankDTO = new CustomerRankDto();
		this.customerRankDTO.setCustomerID(this.customer.getCustomerID());
		this.customerRankDTO.setContractID(this.applyContract.getContractID());
		this.customerRankDTO.setRankID(this.rank.getRankID());

		if (this.rankDAO.create(this.rank) == 1 && this.applyContractDAO.create(this.applyContract) == 1
				&& this.customerDAO.updateInsuranceNum(this.customer) == 1) {
			this.rankDAO.commit();
			this.applyContractDAO.commit();
			this.customerDAO.commit();
			
			if (this.customerRankDAO.create(this.customerRankDTO) == 1) {
				this.customerRankDAO.commit();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean joinApplyContractAndCustomer() {
		this.applyContractDAO = new ApplyContractDaoImpl();
		this.customerDAO = new CustomerDaoImpl();

		this.customerRankDTO = new CustomerRankDto();
		this.customerRankDTO.setCustomerID(this.customer.getCustomerID());
		this.customerRankDTO.setContractID(this.applyContract.getContractID());
		this.customerRankDTO.setRankID(this.rank.getRankID());

		if (this.rankDAO.create(this.rank) == 1 && this.applyContractDAO.create(this.applyContract) == 1
				&& this.customerDAO.create(this.customer) == 1) {
			this.rankDAO.commit();
			this.applyContractDAO.commit();
			this.customerDAO.commit();

			if (this.customerRankDAO.create(this.customerRankDTO) == 1) {
				this.customerRankDAO.commit();
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Contract> searchFailContract(HttpServletRequest request) {
		this.failContractDAO = new FailContractDaoImpl();
		this.failContractDTO = new FailContractDto();
		this.failContractList = null;
		this.failContractDTO.setCustomerName(request.getParameter("customerName"));
		this.failContractDTO.setCustomerPhoneNum(request.getParameter("phoneNum"));
		this.failContractDTO.setInsuranceID(this.insurance.getInsuranceID());

		this.failContractList = this.failContractDAO.retriveFailContract(this.failContractDTO);
		return this.failContractList;
	}

	@Override
	public Contract selectFailContract(HttpServletRequest request) {
		this.selectedFailContract = null;
		this.selectedFailContract = this.failContractList.get(Integer.parseInt(request.getParameter("index")));
		return selectedFailContract;
	}

	@Override
	public boolean rejoin(HttpServletRequest request) {
		if (this.selectedFailContract.getCustomerName().equals(request.getParameter("customerName"))
				&& this.selectedFailContract.getCustomerPhoneNum().equals(request.getParameter("customerPhoneNum"))
				&& this.selectedFailContract.getPaymentCycle() == Integer.parseInt(request.getParameter("paymentCycle"))
				&& this.selectedFailContract.getInsuranceFee() == Integer.parseInt(request.getParameter("insuranceFee"))
				&& this.selectedFailContract.getSecurityFee() == Integer.parseInt(request.getParameter("securityFee"))
				&& this.selectedFailContract.getPeriod() == Integer.parseInt(request.getParameter("period"))) {
			// E3. 수정 사항이 없는 경우
			return false;
		}
		Contract rejoinContract = new Contract();
		rejoinContract.setContractID(this.selectedFailContract.getContractID());
		rejoinContract.setCustomerID(this.selectedFailContract.getCustomerID());
		rejoinContract.setCustomerName(request.getParameter("customerName"));
		rejoinContract.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));
		rejoinContract.setInsuranceID(this.selectedFailContract.getInsuranceID());
		rejoinContract.setInsuranceName(this.selectedFailContract.getInsuranceName());
		rejoinContract.setPaymentCycle(Integer.parseInt(request.getParameter("paymentCycle")));
		rejoinContract.setInsuranceFee(Integer.parseInt(request.getParameter("securityFee")));
		rejoinContract.setSecurityFee(Integer.parseInt(request.getParameter("securityFee")));
		rejoinContract.setPeriod(Integer.parseInt(request.getParameter("period")));

		if (this.applyContractDAO.create(rejoinContract) == 1
				&& this.failContractDAO.delete(this.selectedFailContract) == 1) {
			this.applyContractDAO.commit();
			this.failContractDAO.commit();
			return true;
		}

		return true;
	}

}
