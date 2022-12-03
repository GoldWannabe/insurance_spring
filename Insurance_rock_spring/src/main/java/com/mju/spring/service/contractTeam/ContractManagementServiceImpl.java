package com.mju.spring.service.contractTeam;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ContractAccidentDao;
import com.mju.spring.dao.ContractDao;
import com.mju.spring.dao.CustomerRankDao;
import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;
import com.mju.spring.dto.contractTeam.contractManagement.SelectContractManagementDto;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Customer;

import Model.Customer.Rank;

@Service
public class ContractManagementServiceImpl implements ContractManagementService{

	@Autowired
	ContractDao contractDao;
	
	@Autowired
	ContractAccidentDao contractAccidentDao;
	
	@Autowired
	CustomerRankDao customerRankDao;
	
	private Customer customer;
	private Contract contract;
	
	@Override
	public List<Contract> contractSearch(HttpServletRequest request) {
		SelectContractManagementDto selectContractManagementDto = new SelectContractManagementDto();
		selectContractManagementDto.setCustomerName(request.getParameter("customerName"));
		selectContractManagementDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));
		
		// 계약 DB에서 가져옴.
		List<Contract> selectContractList = this.contractDao.retriveContractManagement(selectContractManagementDto);
		
		return selectContractList;
	}

	@Override
	public List<ContractManagementAccidentDto> searchAccidentHistory(HttpServletRequest request) {
		List<ContractManagementAccidentDto> contractAccidentList = this.contractAccidentDao.retriveContractAccident(request.getParameter("contractID"));
		this.customer = new Customer();
		this.contract = new Contract();
		this.customer.setCustomerID(request.getParameter("customerID"));
		this.contract.setContractID(request.getParameter("contractID"));
		if(!contractAccidentList.isEmpty()) {
			return contractAccidentList;
		}else {
			return null;
		}
		
	}

	@Override
	public void renewRank(HttpServletRequest request) {
		RenewCustomerRankDto renewCustomerRankDto = this.customerRankDao.retriveAllId(this.contract.getContractID());
		
		//customer에 각각 다 custoemrID, contractID(List), rankID(List)가 다 set이 되어있어야함.
		if(this.setRankByID(this.contract.getContractID())) {
			
		}
	}

	public boolean setRankByID(String contractID) {
//		Rank rank = new Rank();
//		for (int i = 0; i < contractID.size(); i++) {
//			if (this.contractID.get(i).equals(contractID)) {
//				ResultSet resultSet = this.rank.retriveByID(this.rankID.get(i));
//				return setRank(resultSet);
//			}
//		}
		return false;
	}

}
