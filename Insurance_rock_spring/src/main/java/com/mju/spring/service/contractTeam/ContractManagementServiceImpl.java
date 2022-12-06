package com.mju.spring.service.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ContractAccidentDao;
import com.mju.spring.dao.ContractDao;
import com.mju.spring.dao.CustomerDao;
import com.mju.spring.dao.CustomerRankDao;
import com.mju.spring.dao.RankDao;
import com.mju.spring.dao.RenewContractDao;
import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.dto.contractTeam.contractManagement.CustomerIDAndInsuranceNumDto;
import com.mju.spring.dto.contractTeam.contractManagement.CustomerNameAndInsuranceNameDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewContractManagementDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;
import com.mju.spring.dto.contractTeam.contractManagement.SelectContractManagementDto;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Customer;
import com.mju.spring.entity.Rank;

@Service
public class ContractManagementServiceImpl implements ContractManagementService {

	@Autowired
	ContractDao contractDao;

	@Autowired
	ContractAccidentDao contractAccidentDao;

	@Autowired
	CustomerRankDao customerRankDao;

	@Autowired
	RankDao rankDao;

	@Autowired
	RenewContractDao renewContractDao;
	

	@Autowired
	CustomerDao customerDao;

	private List<Contract> selectContractList;
	private RenewContractManagementDto renewContractManagementDto;
	private CustomerNameAndInsuranceNameDto customerNameAndInsuranceNameDto;
	@Override
	public List<Contract> contractSearch(HttpServletRequest request) {
		SelectContractManagementDto selectContractManagementDto = new SelectContractManagementDto();
		selectContractManagementDto.setCustomerName(request.getParameter("customerName"));
		selectContractManagementDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));

		// 계약 DB에서 가져옴.
		this.selectContractList = this.contractDao.retriveContractManagement(selectContractManagementDto);

		return selectContractList;
	}

	@Override
	public List<ContractManagementAccidentDto> searchAccidentHistory(HttpServletRequest request) {
		String contractID = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getContractID();
		String customerID = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getCustomerID();
		String insuranceID = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getInsuranceID();
		this.renewContractManagementDto = new RenewContractManagementDto();
		this.renewContractManagementDto.setContractID(contractID);
		this.renewContractManagementDto.setCustomerID(customerID);
		this.renewContractManagementDto.setInsuranceID(insuranceID);
		//보여주기
		this.customerNameAndInsuranceNameDto = new CustomerNameAndInsuranceNameDto();
		String customerName = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getCustomerName();
		String insuranceName = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getInsuranceName();
		this.customerNameAndInsuranceNameDto.setCustomerName(customerName);
		this.customerNameAndInsuranceNameDto.setInsuranceName(insuranceName);
		List<ContractManagementAccidentDto> contractAccidentList = this.contractAccidentDao
				.retriveContractAccident(contractID);

		return contractAccidentList;

	}

	@Override
	public boolean applyRenew(HttpServletRequest request) {
		RenewCustomerRankDto renewCustomerRankDto = this.customerRankDao.retriveAllId(this.renewContractManagementDto.getContractID());
//		int starExist = renewCustomerRankDto.getRankID().indexOf("*") + 1;// 있으면 1 없으면 0
		if (renewCustomerRankDto != null) {

			Rank rank = new Rank();
			rank.setRankID("*" + renewCustomerRankDto.getRankID());
			rank.setMaterial(request.getParameter("material"));
			rank.setFireFacilities(Integer.parseInt(request.getParameter("fireFacilities")));
			rank.setHeight(Boolean.valueOf(request.getParameter("height")));
			rank.setScale(Integer.parseInt(request.getParameter("scale")));
			rank.setSurroundingFacilities(Double.valueOf(request.getParameter("surroundingFacilities")));
			rank.setPurpose(request.getParameter("purpose"));

			// RankID, material, fireFacilities,height,scale,surroundingFacilities,purpose
			this.rankDao.create(rank);
			this.rankDao.commit();
			renewCustomerRankDto.setRankID("*" + renewCustomerRankDto.getRankID());
			this.customerRankDao.insertCustomerRank(renewCustomerRankDto);
			this.customerRankDao.commit();

			this.renewContractManagementDto.setSecurityFee(Integer.parseInt(request.getParameter("securityFee")));
			this.renewContractManagementDto.setInsuranceFee(Integer.parseInt(request.getParameter("insuranceFee")));
			this.renewContractManagementDto.setPaymentCycle(Integer.parseInt(request.getParameter("paymentCycle")));
			this.renewContractManagementDto.setPeriod(Integer.parseInt(request.getParameter("period")));

			this.renewContractDao.insertApplyRenew(this.renewContractManagementDto);
			this.renewContractDao.commit();

			if (renewCustomerRankDto != null && renewCustomerRankDto != null && renewContractManagementDto != null) {
				return true;
			} else {
				//이게 null이면 DB에서 정보를 못받아온건가?
				return false;
			}

		}else {
			return false;
		}
	}

	@Override
	public CustomerNameAndInsuranceNameDto cancelRenew(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 1. 갱신 신청을 한것은 삭제할수없도록 하기
		// 2. 계약 해지는 계약DB에서 완전히 계약을 삭제하고 고객의 가입 보험 개수 를 -1로 수정한다.
		String contractID = this.renewContractManagementDto.getContractID();
		String customerID = this.renewContractManagementDto.getCustomerID();

		RenewCustomerRankDto renewCustomerRankDto = this.customerRankDao.retriveAllId(contractID);
//		int starExist = renewCustomerRankDto.getRankID().indexOf("*") + 1;// 별이 있으면 1 별이 없으면 0
		if (renewCustomerRankDto !=  null) {
			this.contractDao.deleteContractManagement(contractID);
			this.contractDao.commit();
			
			//고객 DB 가져와야함.
			Double insuranceNum =  this.customerDao.selectInsuranceNum(customerID);
			CustomerIDAndInsuranceNumDto customerIDAndInsuranceNumDto = new CustomerIDAndInsuranceNumDto();
			customerIDAndInsuranceNumDto.setCustomerID(customerID);
			customerIDAndInsuranceNumDto.setInsuranceNum(insuranceNum-1);
			
			this.customerDao.updateInsuranceNum(customerIDAndInsuranceNumDto);
			this.customerDao.commit();
			
			return customerNameAndInsuranceNameDto;
		}else {
			return null;			
		}
	}

}
