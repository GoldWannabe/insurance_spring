package com.mju.spring.service.contractTeam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ContractAccidentDao;
import com.mju.spring.dao.ContractDao;
import com.mju.spring.dao.CustomerRankDao;
import com.mju.spring.dao.RankDao;
import com.mju.spring.dto.contractTeam.contractManagement.ContractManagementAccidentDto;
import com.mju.spring.dto.contractTeam.contractManagement.RenewCustomerRankDto;
import com.mju.spring.dto.contractTeam.contractManagement.SelectContractManagementDto;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Customer;
import com.mju.spring.entity.Rank;


@Service
public class ContractManagementServiceImpl implements ContractManagementService{

	@Autowired
	ContractDao contractDao;
	
	@Autowired
	ContractAccidentDao contractAccidentDao;
	
	@Autowired
	CustomerRankDao customerRankDao;
	
	@Autowired
	RankDao rankDao;
	
	private Contract contract;
	private List<Contract> selectContractList;
	
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
		List<ContractManagementAccidentDto> contractAccidentList = this.contractAccidentDao.retriveContractAccident(contractID);
		this.contract = new Contract();
		this.contract.setContractID(contractID);
		
		return contractAccidentList;
		
	}

	@Override
	public boolean renewRank(HttpServletRequest request) {
		RenewCustomerRankDto renewCustomerRankDto = this.customerRankDao.retriveAllId(this.contract.getContractID());
		Rank rank = new Rank();
		
		rank.setRankID("*"+renewCustomerRankDto.getRankID());
		rank.setMaterial(request.getParameter("material"));
		rank.setFireFacilities(Integer.parseInt(request.getParameter("fireFacilities")));
		rank.setHeight(Boolean.valueOf(request.getParameter("height")));
		rank.setScale(Integer.parseInt(request.getParameter("scale")));
		rank.setSurroundingFacilities(Double.valueOf(request.getParameter("surroundingFacilities")));
		rank.setPurpose(request.getParameter("purpose"));
	
		//RankID, material, fireFacilities,height,scale,surroundingFacilities,purpose
		this.rankDao.create(rank);
		this.rankDao.commit();
		renewCustomerRankDto.setRankID("*"+renewCustomerRankDto.getRankID());
		customerRankDao.insertCustomerRank(renewCustomerRankDto);
		this.customerRankDao.commit();
		if(renewCustomerRankDto != null && renewCustomerRankDto != null) {
			return true;
		}else {
			return false;
		}
		//계약 관리 할 때, 갱신 부분을 할 경우 rank 업데이트 하지 말고 현재 랭크 id 맨 앞에 *붙여서 새로 저장해주세요
		
	}

}