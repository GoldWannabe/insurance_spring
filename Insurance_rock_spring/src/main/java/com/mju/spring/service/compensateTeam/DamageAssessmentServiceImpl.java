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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
import com.mju.spring.entity.Insurance.EInsurance;
import com.mju.spring.entity.Provision;

@Service
public class DamageAssessmentServiceImpl implements DamageAssessmentService {

	private Accident accident;
	private List<Accident> selectAccidentList;
//	private Contract contract;
//	private Customer customer;
	private Provision provision;

	@Autowired
	ResourceLoader resourceLoader;

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

	private List<Contract> selectContractList;

	@Override
	public List<Contract> addcheck(HttpServletRequest request) {
		SelectContractDto selectContractDto = new SelectContractDto();

		this.accident = new Accident();

		selectContractDto.setCustomerName(request.getParameter("customerName"));
		selectContractDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));

		// ?????? DB?????? ?????????.
		this.selectContractList = this.contractDao.retriveNameAndPhoneNum(selectContractDto);

		this.accident.setCustomerName(selectContractDto.getCustomerName());
		this.accident.setCustomerPhoneNum(selectContractDto.getCustomerPhoneNum());

		return selectContractList;
	}

	@Override
	public void setSelectContract(HttpServletRequest request) {
		String contractID = this.selectContractList.get(Integer.parseInt(request.getParameter("index")))
				.getContractID();
		String customerID = this.selectContractList.get(Integer.parseInt(request.getParameter("index")))
				.getCustomerID();
		this.accident.setContractID(contractID);
		this.accident.setCustomerID(customerID);
	}

	@Override
	public Accident addAccident(HttpServletRequest request) {

		int liablityRate = Integer.parseInt(request.getParameter("liablityRate"));
		int totalCost = Integer.parseInt(request.getParameter("totalCost"));

		int liablityCost = totalCost * liablityRate / 100;
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

		// +????????????, ????????? , ????????????, ????????????(true,false), accidentID, customerID, contractID
		this.accidentDao.create(this.accident);
		this.accidentDao.commit();

		ContractAccidentDto contractAccidentDto = new ContractAccidentDto();
		contractAccidentDto.setAccidentID(this.accident.getAccidentID());
		contractAccidentDto.setContractID(this.accident.getContractID());

		contractAccidentDao.insertContractProvision(contractAccidentDto);
		this.contractAccidentDao.commit();

		return accident;
	}

	@Override
	public List<Accident> searchAccident(HttpServletRequest request) {
		// ????????????, ????????????, ?????????, ????????????, ????????????, ?????????, ????????????, ????????????, ????????????, ????????????, ????????????
		SelectAccidentDto selectAccidentDto = new SelectAccidentDto();
		selectAccidentDto.setCustomerName(request.getParameter("customerName"));
		selectAccidentDto.setAccidentDate(LocalDate.parse(request.getParameter("accidentDate")));

		// ??????DB
		this.selectAccidentList = this.accidentDao.retriveNameAndDate(selectAccidentDto);

		return selectAccidentList;
	}

	@Override
	public Accident getSelectAccident(HttpServletRequest request) {
		return selectAccident(request);
		// ????????????, ?????????, ??????????????? ????????? ?????????????????? ?????????????????????????
		// ????????? ?????? ????????? ??????????????? ????????????.
	}

	public Accident selectAccident(HttpServletRequest request) {
		String[] array = request.getParameter("select").split(" ");
		String accidentID = this.selectAccidentList.get(Integer.parseInt(array[1])).getAccidentID();

		for (Accident accident : selectAccidentList) {
			if (accident.getAccidentID() == accidentID) {
				this.accident = accident;
				return accident;
			}
		}
		return null;
	}

	@SuppressWarnings("resource")
	@Override
	public Provision payCompensation() {
		// ??????ID
		// ??????????????? ????????????, ??????ID,
		CustomerBankDto customerBankDto = this.customerDao.retrivecustomerBank(this.accident.getCustomerID());
		// ??????id, ????????????, ?????????, ?????????, ?????????, ?????????
		ContractProvisionDto contractProvisionDto = this.contractDao.retriveContract(this.accident.getContractID());

		this.provision = new Provision();
		int insuranceBankCost = 0;
		Scanner scanner;
		try {
			Resource resourceInsurance = resourceLoader.getResource("classpath:File//InsuranceBank.txt");
			String insuranceBankPath = resourceInsurance.getURI().getPath();

			File file = new File(insuranceBankPath);
			scanner = new Scanner(file);
			insuranceBankCost = scanner.nextInt();

			System.out.println(insuranceBankCost);
			int result = insuranceBankCost - this.accident.getLiablityCost();

			if (result <= 0) {
				provision.setBankName("????????????");
				return provision;
			}

			FileWriter fileWriter = new FileWriter(file, false);
			String resultToString = Integer.toString(result);
			fileWriter.write(resultToString);
			fileWriter.flush();
			fileWriter.close();

			Resource resourceCustomer = resourceLoader.getResource("classpath:File//CustomerBank.txt");
			String customerBankPath = resourceCustomer.getURI().getPath();

			fileWriter = new FileWriter(customerBankPath, false);
			String resultToString2 = Integer.toString(this.accident.getLiablityCost());
			fileWriter.write(resultToString2);
			fileWriter.flush();
			fileWriter.close();
			if (resultToString2 == null) {
				provision.setBankName("????????????");
				return provision;
			}

			if (!(this.accident.isPayCompleted())) {
				this.accident.setPayCompleted(true);
				this.accidentDao.updatePaycompleted(this.accident);
				this.accidentDao.commit();
			} else {
				provision.setBankName("??????????????????");
				return provision;
			}

			LocalDate startDate = contractProvisionDto.getStartDate();
			int StartYear = startDate.getYear();
			LocalDate endDate = contractProvisionDto.getEndDate();
			int endYear = endDate.getYear();

			String getInsuranceType = this.insuranceDao.retriveInsuranceType(contractProvisionDto.getInsuranceID());
			EInsurance insuranceType = EInsurance.valueOf(getInsuranceType);
			provision.setInsuranceType(insuranceType);

			// ?????????-???????????? 3??????????????? false ????????? true ??????.
			if (endYear - StartYear >= 3) {
				provision.setLongTerm(true);
			} else {
				provision.setLongTerm(false);
			}

			// A2. ???????????? ?????? ????????? ????????? ?????? ?????? ??????
			int overCost = contractProvisionDto.getSecurityFee() - contractProvisionDto.getProvisionFee();
			if (overCost <= 0) {
				provision.setCompensation(contractProvisionDto.getSecurityFee());
			} else {
				provision.setCompensation(this.accident.getLiablityCost());
			}

			// A3. ?????? ??????????????? ?????? ????????? ???????????? 20% ????????? ??????
			if (!provision.isLongTerm()
					&& this.accident.getLiablityCost() < contractProvisionDto.getSecurityFee() * 0.2) {
//				System.out.println(this.accident.getCustomerName() + "?????? ????????????????????? ?????? ??????????????? ???????????? 20%??????????????? ?????? ?????????????????????.");
				//?????? ??????????????? ?????????????????? ????????????.
			} else {
				// ?????? Dto???????????? ?????????,,,?????????????????????.. ??????ID, provisionFee, liablityCost
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
			provision.setCustomerPhoneNum(this.accident.getCustomerPhoneNum());
			provision.setInsuranceName(contractProvisionDto.getInsuranceName());
			provision.setBankName(customerBankDto.getBankName());
			provision.setAccountNum(customerBankDto.getAccountNum());
			provision.setCompensationDate(LocalDate.now());
			// ??????ID, ??????ID, ????????????, ?????????, ????????????,?????????, ????????????, ????????????.????????????, ????????????, ????????????, ??????ID??? ??????.
			provisionDao.insertProvision(provision);
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

	@Override
	public boolean modify(HttpServletRequest request) {
		this.accident.setAccidentID(this.accident.getAccidentID());
		this.accident.setAccidentDate(LocalDate.parse(request.getParameter("accidentDate")));
		this.accident.setContent(request.getParameter("content"));
		this.accident.setKindOfCost(request.getParameter("kindOfCost"));
		this.accident.setDamagePer(Integer.parseInt(request.getParameter("damagePer")));
		this.accident.setTotalCost(Integer.parseInt(request.getParameter("totalCost")));
		this.accident.setLiablityCost(Integer.parseInt(request.getParameter("liablityCost")));
		this.accident.setLiablityRate(Integer.parseInt(request.getParameter("liablityRate")));

		boolean checkModification = this.accidentDao.updateAccidentInfo(this.accident);
		this.accidentDao.commit();
		if (checkModification) {
			return true;
		} else {
			return false;
		}
	}

}
