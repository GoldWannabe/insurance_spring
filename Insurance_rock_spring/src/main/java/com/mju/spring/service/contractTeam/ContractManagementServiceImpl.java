package com.mju.spring.service.contractTeam;

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

@Service
public class ContractManagementServiceImpl implements ContractManagementService{

	@Autowired
	ContractDao contractDao;
	
	@Autowired
	ContractAccidentDao contractAccidentDao;
	
	@Autowired
	CustomerRankDao customerRankDao;
	
	private Customer customer;
	
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
		this.customer.setCustomerID(request.getParameter("customerID"));

		if(!contractAccidentList.isEmpty()) {
			return contractAccidentList;
		}else {
			return null;
		}
		
	}

	@Override
	public void renewRank(HttpServletRequest request) {
		List<RenewCustomerRankDto> renewCustomerRankList = this.customerRankDao.retriveAllId(this.customer.getCustomerID());
		
	}

}
