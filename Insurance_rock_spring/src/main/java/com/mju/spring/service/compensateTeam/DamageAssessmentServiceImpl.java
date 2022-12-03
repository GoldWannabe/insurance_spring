package com.mju.spring.service.compensateTeam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.AccidentDao;
import com.mju.spring.dao.ContractAccidentDao;
import com.mju.spring.dao.ContractDao;
import com.mju.spring.dao.CustomerDao;
import com.mju.spring.dao.InsuranceDao;
import com.mju.spring.dao.ProvisionDao;
import com.mju.spring.dto.damageAssessment.compansate.ContractAccidentDto;
import com.mju.spring.dto.damageAssessment.compansate.ContractProvisionDto;
import com.mju.spring.dto.damageAssessment.compansate.CustomerBankDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectAccidentDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectContractDto;
import com.mju.spring.dto.damageAssessment.compansate.UpdateContractDto;
import com.mju.spring.entity.Accident;
import com.mju.spring.entity.Contract;
import com.mju.spring.entity.Provision;



@Service
public class DamageAssessmentServiceImpl implements DamageAssessmentService {

	private Accident accident;
	private List<Accident> selectAccidentList;
//	private Contract contract;
//	private Customer customer;
	private Provision provision;

	@Autowired
	ContractDao contractDao;

	@Autowired
	AccidentDao accidentDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	InsuranceDao insuranceDao;

	@Autowired
	ContractAccidentDao contractAccidentDao;

	@Autowired
	ProvisionDao provisionDao;

	public List<Contract> addcheck(HttpServletRequest request) {
		SelectContractDto selectContractDto = new SelectContractDto();
		
		this.accident = new Accident();
		
		selectContractDto.setCustomerName(request.getParameter("customerName"));
		selectContractDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));
		
		// 계약 DB에서 가져옴.
		List<Contract> selectContractList = this.contractDao.retriveNameAndPhoneNum(selectContractDto);
		
		this.accident.setCustomerName(selectContractDto.getCustomerName());
		this.accident.setCustomerPhoneNum(selectContractDto.getCustomerPhoneNum());

		return selectContractList;
	}
	
	@Override
	public void setSelectContract(HttpServletRequest request) {
		this.accident.setContractID(request.getParameter("contractID"));
		this.accident.setCustomerID(request.getParameter("customerID"));
	}
	
	

	public Accident addAccident(HttpServletRequest request) {

		int liablityRate = Integer.parseInt(request.getParameter("liablityRate"));
		int totalCost = Integer.parseInt(request.getParameter("totalCost"));

		int liablityCost = totalCost*liablityRate / 100; 
		this.accident.setAccidentID(UUID.randomUUID().toString());
		this.accident.setCustomerID(this.accident.getCustomerID());
		this.accident.setContractID(this.accident.getContractID());
		this.accident.setAccidentDate(LocalDate.parse(request.getParameter("accidentDate")));
		this.accident.setContent(request.getParameter("content"));
		this.accident.setKindOfCost(request.getParameter("kindOfCost"));
		this.accident.setDamagePer(Integer.parseInt(request.getParameter("damagePer")));
		this.accident.setTotalCost(totalCost);
		this.accident.setLiablityRate(liablityRate);
		this.accident.setLiablityCost(liablityCost);
		this.accident.setPayCompleted(false);


		// +고객이름, 연락처 , 책임비용, 지급여부(true,false), accidentID, customerID, contractID
		this.accidentDao.create(this.accident);
		this.accidentDao.commit();
		
		ContractAccidentDto contractAccidentDto = new ContractAccidentDto();
		contractAccidentDto.setAccidentID(this.accident.getAccidentID());
		contractAccidentDto.setContractID(this.accident.getContractID());
		
		contractAccidentDao.insertContractProvision(contractAccidentDto);
		this.contractAccidentDao.commit();

		
		return accident;
	}

	public List<Accident> searchAccident(HttpServletRequest request) {
		// 가입자명, 연락처, 사고날짜, 사고내용, 총비용, 손해정도, 비용종류, 지급여뷰, 책임비율, 책임비용
		this.selectAccidentList.clear();
		SelectAccidentDto selectAccidentDto = new SelectAccidentDto();
		selectAccidentDto.setCustomerName(request.getParameter("customerName"));
		selectAccidentDto.setAccidentDate(LocalDate.parse(request.getParameter("accidentDate")));

		// 사고DB
		this.selectAccidentList = this.accidentDao.retriveNameAndDate(selectAccidentDto);

		return selectAccidentList;
	}

	public Accident selectAccident(HttpServletRequest request) {
		for (Accident accident : selectAccidentList) {
			if (accident.getAccidentID() == request.getParameter("accientID")) {
				this.accident = accident;
				return accident;
			}
		}
		return null;
	}

	public Provision payCompensation() {
		// 사고ID
		// 계좌번호랑 은행이름, 고객ID,
		CustomerBankDto customerBankDto = this.customerDao.retrivecustomerBank(this.accident.getCustomerID());
		// 보험id, 보험이름, 담보액, 지급액, 시작일, 만기일
		ContractProvisionDto contractProvisionDto = this.contractDao.retriveContract(this.accident.getContractID());

		int insuranceBankCost = 0;
		@SuppressWarnings("resource")
		Scanner scanner;
		try {
			File file = new File(".//File//InsuranceBank.txt");
			scanner = new Scanner(file);
			insuranceBankCost = scanner.nextInt();

			int result = insuranceBankCost - this.accident.getLiablityCost();

			if (result <= 0) {
				System.out.println("보험통장의 잔액이 부족합니다.");
			} else {
				FileWriter fileWriter = new FileWriter(file, false);
				String resultToString = Integer.toString(result);
				fileWriter.write(resultToString);
				fileWriter.flush();
				fileWriter.close();

				fileWriter = new FileWriter(".//File//CustomerBank.txt", false);
				String resultToString2 = Integer.toString(this.accident.getLiablityCost());
				fileWriter.write(resultToString2);
				fileWriter.flush();
				fileWriter.close();
				if (resultToString2 == null) {
					System.out.println("통장에 문제가 생겼습니다. 관련팀(1234-5678)에 최대한 빠르게 연락바랍니다.");
				}
			}
			if (!(this.accident.isPayCompleted())) {
				this.accident.setPayCompleted(true);
				this.accidentDao.updatePaycompleted(this.accident);
			}

			this.provision = new Provision();

			LocalDate startDate = contractProvisionDto.getStartDate();
			int StartYear = startDate.getYear();
			LocalDate endDate = contractProvisionDto.getEndDate();
			int endYear = endDate.getYear();

//			String insuranceType = this.insuranceDao.retriveInsuranceType(contractProvisionDto.getInsuranceID());
//			EInsurance einsuranceType = EInsurance.valueOf(insuranceType);
//			provision.setInsuranceType(einsuranceType);

			// 만료일-시작일이 3년이상이면 false 아니면 true 입력.
			if (endYear - StartYear >= 3) {
				provision.setLongTerm(true);
			} else {
				provision.setLongTerm(false);
			}

			// A2. 담보액을 넘는 비용을 보상해 줘야 하는 경우
			int overCost = contractProvisionDto.getSecurityFee() - contractProvisionDto.getProvisionFee();
			if (overCost <= 0) {
				System.out.println("<<담보액을 넘는 금액입니다. 담보액까지만 지급합니다.>>");
				provision.setCompensation(contractProvisionDto.getSecurityFee());
			} else {
				provision.setCompensation(this.accident.getLiablityCost());
			}

			// A3. 장기 가입자이고 책임 비용이 담보액의 20% 미만인 경우
			if (!provision.isLongTerm() && this.accident.getLiablityCost() < contractProvisionDto.getSecurityFee() * 0.2) {
				System.out.println(this.accident.getCustomerName() + "님은 장기가입자이며 현재 보상금액이 담보액의 20%미만이므로 무료 대상자이십니다.");
			} else {
				//얘는 Dto만드는게 나을듯,,,타입맞춰야해서.. 계약ID, provisionFee, liablityCost
				UpdateContractDto updateContractDto = new UpdateContractDto();
						
				updateContractDto.setContractID(this.accident.getContractID());
				updateContractDto.setProvisionFee(contractProvisionDto.getProvisionFee());
				updateContractDto.setLiablityCost(this.accident.getLiablityCost());
				
				contractDao.updateContractProvisionFee(updateContractDto);
				this.contractDao.commit();
			}

			provision.setProvisionID(UUID.randomUUID().toString());
			provision.setCustomerID(this.accident.getCustomerID());
			provision.setContractID(this.accident.getContractID());
			provision.setCustomerName(this.accident.getCustomerName());
			provision.setPhoneNum(this.accident.getCustomerPhoneNum());
			provision.setInsuranceName(contractProvisionDto.getInsuranceName());
			provision.setBankName(customerBankDto.getBankName());
			provision.setAccountNum(customerBankDto.getAccountNum());
			provision.setCompensationDate(LocalDate.now());
			// 지급ID, 고객ID, 가입자명, 연락처, 계좌번호,은행명, 보상금액, 지급날짜.보험이름, 장기여부, 보험종류, 계약ID를 저장.
			provisionDao.inserNeProvision(provision);
			this.provisionDao.commit();
			
			scanner.close();
			return provision;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
