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

		// 계약 DB에서 가져옴.
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

	@Override
	public List<Accident> searchAccident(HttpServletRequest request) {
		// 사고번호, 가입자명, 연락처, 사고날짜, 사고내용, 총비용, 손해정도, 비용종류, 지급여뷰, 책임비율, 책임비용
		SelectAccidentDto selectAccidentDto = new SelectAccidentDto();
		selectAccidentDto.setCustomerName(request.getParameter("customerName"));
		selectAccidentDto.setAccidentDate(LocalDate.parse(request.getParameter("accidentDate")));

		// 사고DB
		this.selectAccidentList = this.accidentDao.retriveNameAndDate(selectAccidentDto);

		return selectAccidentList;
	}

	@Override
	public Accident getSelectAccident(HttpServletRequest request) {
		return selectAccident(request);
		// 가입자명, 연락처, 사고번호의 사고의 책임비용원을 지급하시겠습니까?
		// 보상급 지급 여부와 책임비용을 요청한다.
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
		// 사고ID
		// 계좌번호랑 은행이름, 고객ID,
		CustomerBankDto customerBankDto = this.customerDao.retrivecustomerBank(this.accident.getCustomerID());
		// 보험id, 보험이름, 담보액, 지급액, 시작일, 만기일
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
				provision.setBankName("잔고부족");
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
				provision.setBankName("통장문제");
				return provision;
			}

			if (!(this.accident.isPayCompleted())) {
				this.accident.setPayCompleted(true);
				this.accidentDao.updatePaycompleted(this.accident);
				this.accidentDao.commit();
			} else {
				provision.setBankName("이미보상완료");
				return provision;
			}

			LocalDate startDate = contractProvisionDto.getStartDate();
			int StartYear = startDate.getYear();
			LocalDate endDate = contractProvisionDto.getEndDate();
			int endYear = endDate.getYear();

			String getInsuranceType = this.insuranceDao.retriveInsuranceType(contractProvisionDto.getInsuranceID());
			EInsurance insuranceType = EInsurance.valueOf(getInsuranceType);
			provision.setInsuranceType(insuranceType);

			// 만료일-시작일이 3년이상이면 false 아니면 true 입력.
			if (endYear - StartYear >= 3) {
				provision.setLongTerm(true);
			} else {
				provision.setLongTerm(false);
			}

			// A2. 담보액을 넘는 비용을 보상해 줘야 하는 경우
			int overCost = contractProvisionDto.getSecurityFee() - contractProvisionDto.getProvisionFee();
			if (overCost <= 0) {
				provision.setCompensation(contractProvisionDto.getSecurityFee());
			} else {
				provision.setCompensation(this.accident.getLiablityCost());
			}

			// A3. 장기 가입자이고 책임 비용이 담보액의 20% 미만인 경우
			if (!provision.isLongTerm()
					&& this.accident.getLiablityCost() < contractProvisionDto.getSecurityFee() * 0.2) {
//				System.out.println(this.accident.getCustomerName() + "님은 장기가입자이며 현재 보상금액이 담보액의 20%미만이므로 무료 대상자이십니다.");
				//그냥 보상해주고 지급내역에서 깍지않음.
			} else {
				// 얘는 Dto만드는게 나을듯,,,타입맞춰야해서.. 계약ID, provisionFee, liablityCost
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
			// 지급ID, 고객ID, 가입자명, 연락처, 계좌번호,은행명, 보상금액, 지급날짜.보험이름, 장기여부, 보험종류, 계약ID를 저장.
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
