package com.mju.spring.service.contractTeam;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.AccidentDao;
import com.mju.spring.dao.ContractAccidentDao;
import com.mju.spring.dao.ContractDao;
import com.mju.spring.dao.CustomerDao;
import com.mju.spring.dao.CustomerRankDao;
import com.mju.spring.dao.RankDao;
import com.mju.spring.dao.RenewContractDao;
import com.mju.spring.dto.contractTeam.contractManagement.CustomerIDAndInsuranceNumDto;
import com.mju.spring.dto.contractTeam.contractManagement.InsuranceDetailsDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewContractManagementDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerPopupDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;
import com.mju.spring.dto.contractTeam.contractManagement.SelectContractManagementDto;
import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;
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
	AccidentDao accidentDao;
	
	@Autowired
	RenewContractDao renewContractDao;
	

	@Autowired
	CustomerDao customerDao;

	private List<Contract> selectContractList;
	private RenewContractManagementDto renewContractManagementDto;
	private RenewCustomerPopupDto renewCustomerPopupDto;
	private InsuranceDetailsDto insuranceDetailsDto;
	@Override
	public List<Contract> contractSearch(HttpServletRequest request) {
		SelectContractManagementDto selectContractManagementDto = new SelectContractManagementDto();
		selectContractManagementDto.setCustomerName(request.getParameter("customerName"));
		selectContractManagementDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));

		// ?????? DB?????? ?????????.
		this.selectContractList = this.contractDao.retriveContractManagement(selectContractManagementDto);

		return selectContractList;
	}
	@Override
	public InsuranceDetailsDto searchInsuranceDetails(HttpServletRequest request) {
		String contractID = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getContractID();
		String customerID = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getCustomerID();
		String insuranceID = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getInsuranceID();
		String insuranceName =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getInsuranceName();
		String customerName =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getCustomerName();
		String customerPhoneNum =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getCustomerPhoneNum();
		LocalDate startDate =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getStartDate();
		LocalDate endDate =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getEndDate();
		int securityFee =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getSecurityFee();
		int insuranceFee =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getInsuranceFee();
		int paymentCycle =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getPaymentCycle();
		int unpaidFee =  this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getUnpaidFee();
		
		RenewCustomerRankDto renewCustomerRankDto = this.customerRankDao.retriveAllId(contractID);
		//?????? ?????? ????????? ?????? Dto
		this.insuranceDetailsDto = new InsuranceDetailsDto();
		this.insuranceDetailsDto.setContractID(contractID);
		this.insuranceDetailsDto.setInsuranceName(insuranceName);
		this.insuranceDetailsDto.setCustomerName(customerName);
		this.insuranceDetailsDto.setCustomerPhoneNum(customerPhoneNum);
		this.insuranceDetailsDto.setStartDate(startDate);
		this.insuranceDetailsDto.setEndDate(endDate);
		this.insuranceDetailsDto.setSecurityFee(securityFee);
		this.insuranceDetailsDto.setInsuranceFee(insuranceFee);
		this.insuranceDetailsDto.setPaymentCycle(paymentCycle);
		this.insuranceDetailsDto.setUnpaidFee(unpaidFee);
		
		if (renewCustomerRankDto != null) {
			String rankID = renewCustomerRankDto.getRankID();
			
			Rank rank = new Rank();
			rank =  this.rankDao.retriveRankById(rankID);
			this.insuranceDetailsDto.setMaterial(rank.getMaterial());
			this.insuranceDetailsDto.setFireFacilities(rank.getFireFacilities());
			this.insuranceDetailsDto.setHeight(rank.isHeight());
			this.insuranceDetailsDto.setScale(rank.getScale());
			this.insuranceDetailsDto.setSurroundingFacilities(rank.getSurroundingFacilities());
			this.insuranceDetailsDto.setPurpose(rank.getPurpose());
			
		
		}else {
			return null;
		}
		
		//?????? set ????????? ?????? Dto
		this.renewContractManagementDto = new RenewContractManagementDto();
		this.renewContractManagementDto.setContractID(contractID);
		this.renewContractManagementDto.setCustomerID(customerID);
		this.renewContractManagementDto.setInsuranceID(insuranceID);
		
		//?????? set ???????????? ????????????
		this.renewCustomerPopupDto = new RenewCustomerPopupDto();
		this.renewCustomerPopupDto.setCustomerName(customerName);
		this.renewCustomerPopupDto.setInsuranceName(insuranceName);
		this.renewCustomerPopupDto.setCustomerPhoneNum(customerPhoneNum);
		
		//?????? 1?????? ?????? ?????????.
		//????????????,????????????, ????????????, ?????????, ???????????????,?????? ?????????,?????????,?????????,????????????, ?????????,??????, ????????????
		//????????? ?????? ????????? ???????????? ?????????(material, fireFacilities, height, scale,  surroundingFacilities, purpose)RankID??? ????????????.
		//??????????????? ????????? ?????? ???????????? ??????(accidentID, contractID),accidentID??? accident?????? ?????? ??? ????????????.
		return insuranceDetailsDto;
	}

	@Override
	public List<Accident> searchAccidentHistory(HttpServletRequest request) {
		String contractID = this.selectContractList.get(Integer.parseInt(request.getParameter("index"))).getContractID();
	
		
		List<Accident> accidentList = this.accidentDao.retriveAccidentByContractId(contractID);
		
		return accidentList;
	}
	@Override
	public InsuranceDetailsDto getRenewInfo() {
		return insuranceDetailsDto;
	}

	@Override
	public boolean applyRenew(HttpServletRequest request) {
		RenewCustomerRankDto renewCustomerRankDto = this.customerRankDao.retriveAllId(this.renewContractManagementDto.getContractID());
//		int starExist = renewCustomerRankDto.getRankID().indexOf("*") + 1;// ????????? 1 ????????? 0
		if (renewCustomerRankDto != null) {

			Rank rank = new Rank();
			rank.setRankID("*" + renewCustomerRankDto.getRankID());
			rank.setMaterial(request.getParameter("material"));
			rank.setFireFacilities(Double.valueOf(request.getParameter("fireFacilities")));
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
				//?????? null?????? DB?????? ????????? ???????????????????
				return false;
			}

		}else {
			return false;
		}
	}

	@Override
	public RenewCustomerPopupDto cancelRenew(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 1. ?????? ????????? ????????? ????????????????????? ??????
		// 2. ?????? ????????? ??????DB?????? ????????? ????????? ???????????? ????????? ?????? ?????? ?????? ??? -1??? ????????????.
		String contractID = this.renewContractManagementDto.getContractID();
		String customerID = this.renewContractManagementDto.getCustomerID();

		RenewCustomerRankDto renewCustomerRankDto = this.customerRankDao.retriveAllId(contractID);
//		int starExist = renewCustomerRankDto.getRankID().indexOf("*") + 1;// ?????? ????????? 1 ?????? ????????? 0
		if (renewCustomerRankDto !=  null) {
			this.contractDao.deleteContractManagement(contractID);
			this.contractDao.commit();
			
			//?????? DB ???????????????.
			Double insuranceNum =  this.customerDao.selectInsuranceNum(customerID);
			if(insuranceNum == null) {
				renewCustomerPopupDto.setCustomerName("??????????????????????????????");
				return renewCustomerPopupDto;
			}else if(insuranceNum < 1){
				this.customerDao.deleteInsuranceNum(customerID);
				this.customerDao.commit();
				
				return renewCustomerPopupDto;
			}else {
				CustomerIDAndInsuranceNumDto customerIDAndInsuranceNumDto = new CustomerIDAndInsuranceNumDto();
				customerIDAndInsuranceNumDto.setCustomerID(customerID);
				customerIDAndInsuranceNumDto.setInsuranceNum(insuranceNum-1);
				
				this.customerDao.updateInsuranceNum(customerIDAndInsuranceNumDto);
				this.customerDao.commit();
				
				return renewCustomerPopupDto;
				
			}
		}else {
			return null;			
		}
	}
	



}
