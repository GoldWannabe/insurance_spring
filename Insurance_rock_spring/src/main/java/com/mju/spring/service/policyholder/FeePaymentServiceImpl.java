package com.mju.spring.service.policyholder;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JEditorPane;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.mju.spring.dao.ContractDao;
import com.mju.spring.dao.CustomerDao;
import com.mju.spring.dao.InsuranceDao;
import com.mju.spring.dao.PaymentDao;
import com.mju.spring.dao.ProvisionDao;
import com.mju.spring.dto.policyholder.feePayment.AccountDto;
import com.mju.spring.dto.policyholder.feePayment.ContractAccountDto;
import com.mju.spring.dto.policyholder.feePayment.DuePaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.dto.policyholder.feePayment.ProvisionDto;
import com.mju.spring.dto.policyholder.feePayment.UnpaideFeeDto;
import com.mju.spring.entity.Customer;
import com.mju.spring.entity.Payment;
import com.mju.spring.exception.ChangeDateException;
import com.mju.spring.exception.FailPaymentExcaption;
import com.mju.spring.exception.FileAcceptException;
import com.mju.spring.exception.LackMoneyException;
import com.mju.spring.exception.NotFindBank;
import com.mju.spring.exception.NotFindPolicyholderException;
import com.mju.spring.exception.UnderMinimunMoneyException;

@Service
public class FeePaymentServiceImpl implements FeePaymentService {

	@Autowired
	ContractDao contractDao;
	@Autowired
	PaymentDao paymentDao;
	@Autowired
	ProvisionDao provisionDao;
	@Autowired
	InsuranceDao insuranceDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	ResourceLoader resourceLoader;

	private List<DuePaymentDto> duePaymentList;
	private PolicyholderDto policyholderDto;
	private Customer customer;
	private AccountDto accountDto;
	private UnpaideFeeDto unpaidFeeDto;
	private List<Payment> tempPaymentList;

	@Override
	public List<DuePaymentDto> getDuePaymentList(HttpServletRequest request) {
		LocalDate today = LocalDate.parse(request.getParameter("today"));
		LocalDate nowDate = LocalDate.now();
		if((today.getMonthValue()-nowDate.getMonthValue()) != 0) {
			throw new ChangeDateException();
		}
		
		policyholderDto = new PolicyholderDto();
		policyholderDto.setCustomerName(request.getParameter("customerName"));
		policyholderDto.setCustomerPhoneNum(request.getParameter("customerPhoneNum"));
		this.duePaymentList = this.contractDao.retrivePayment(policyholderDto);
		checkFullPayment();

		return this.duePaymentList;
	}

	private void checkFullPayment() {
		// 일시불 여부
		if(this.duePaymentList == null) {
			throw new NotFindPolicyholderException();
		}
		
		for (DuePaymentDto duePaymentDto : this.duePaymentList) {
			if (duePaymentDto.getInsuranceFee() <= duePaymentDto.getUnpaidFee()) {
				duePaymentDto.setFullPayment(true);
			} else if (duePaymentDto.getInsuranceFee() > duePaymentDto.getUnpaidFee()) {
				duePaymentDto.setFullPayment(false);
			} else {
				return;
			}
		}
	}

	@Override
	public AccountDto getAccountAndSetCustomer() {
		String customerID = duePaymentList.get(0).getCustomerID();
		this.customer = this.customerDao.retriveCustomerById(customerID);
		this.accountDto = new AccountDto();
		accountDto.setBankName(this.customer.getBankName());
		accountDto.setAccountNum(this.customer.getAccountNum());
		accountDto.setCheckBank(true);
		accountDto.setCheckCard(false);
		return accountDto;
	}

	@Override
	public List<PaymentDto> getPaymentRecord() {
		return this.paymentDao.retrivePayment(this.policyholderDto);
	}

	@Override
	public List<ProvisionDto> getProvisionRecord() {
		return this.provisionDao.retriveProvision(this.policyholderDto);
	}

	@Override
	public AccountDto setAccount(HttpServletRequest request) {

		accountDto.setBankName(request.getParameter("AccountName"));
		accountDto.setAccountNum(request.getParameter("AccountNum"));
		if (request.getParameter("method").equals("card")) {
			accountDto.setCheckBank(false);
			accountDto.setCheckCard(true);
		} else if (request.getParameter("method").equals("bank")) {
			accountDto.setCheckBank(true);
			accountDto.setCheckCard(false);
		} else {
			return null;
		}
		return this.accountDto;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean checkBank() {
		int money = 0;

		try {
			Resource resourceCustomer = resourceLoader.getResource("classpath:File//CustomerBank.txt");
			String customerBankPath = resourceCustomer.getURI().getPath();
			File file = new File(customerBankPath);
			Scanner scanner = new Scanner(file);
			String amount = scanner.next();
			money = Integer.parseInt(amount);
		} catch (IOException | InputMismatchException e) {
			throw new NotFindBank();
		}
		if (money <= 1000) {
			throw new UnderMinimunMoneyException();
		} else {
			return true;
		}
	}

	@Override
	public AccountDto getAccount() {
		return this.accountDto;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean feeFullPayment() {
		int totalFee = 0;
		int customerMoney = 0;
		for (DuePaymentDto duePaymentDto : this.duePaymentList) {
			totalFee = duePaymentDto.getUnpaidFee() + totalFee;
		}

		try {
			Resource resourceCustomer = resourceLoader.getResource("classpath:File//CustomerBank.txt");
			String customerBankPath = resourceCustomer.getURI().getPath();
			File file = new File(customerBankPath);
			Scanner scanner = new Scanner(file);
			String tempMoney = scanner.next();
			customerMoney = Integer.parseInt(tempMoney);
			if (totalFee > customerMoney) {
				throw new LackMoneyException();
			}
			customerMoney = customerMoney - totalFee;
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(customerMoney);
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException | InputMismatchException e) {
			throw new FailPaymentExcaption();
		}

		try {
			Resource resourceCustomer = resourceLoader.getResource("classpath:File//InsuranceBank.txt");
			String customerBankPath = resourceCustomer.getURI().getPath();
			File file = new File(customerBankPath);
			Scanner scanner = new Scanner(file);
			String tempMoney = scanner.next();
			int insuranceMoney = Integer.parseInt(tempMoney);
			insuranceMoney = customerMoney + insuranceMoney;
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(insuranceMoney);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException | InputMismatchException e) {
			throw new FailPaymentExcaption();
		}
		this.tempPaymentList = new ArrayList<Payment>();
		for (DuePaymentDto duePaymentDto : this.duePaymentList) {
			Payment payment = new Payment();
			payment.setPaymentID(UUID.randomUUID().toString());
			payment.setCustomerID(this.customer.getCustomerID());
			payment.setCustomerName(this.customer.getName());
			payment.setCustomerPhoneNum(this.customer.getPhoneNum());
			payment.setAccountNum(this.accountDto.getAccountNum());
			payment.setCardOrBankName(this.accountDto.getBankName());
			payment.setInsuranceFee(duePaymentDto.getUnpaidFee());
			payment.setInsuranceName(duePaymentDto.getInsuranceName());
			payment.setPaidDate(LocalDate.now());
			payment.setContractID(duePaymentDto.getContractID());
			payment.setInsuranceType(this.insuranceDao.retriveInsuranceType(duePaymentDto.getInsuranceID()));
			this.tempPaymentList.add(payment);
			if (this.paymentDao.insertPayment(payment) == 1) {
				this.paymentDao.commit();
			} else {
				throw new PersistenceException();
			}

			ContractAccountDto contractAccountDto = new ContractAccountDto();
			contractAccountDto.setContractID(duePaymentDto.getContractID());
			contractAccountDto.setUnpaidFee(0);
			if (this.contractDao.updateUnpaidFee(contractAccountDto) == 1) {
				this.contractDao.commit();
			} else {
				throw new PersistenceException();
			}

		}

		return true;
	}

	@Override
	public List<DuePaymentDto> getDuePaymentList() {
		return this.duePaymentList;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean feePartPayment(HttpServletRequest request) {
		DuePaymentDto duePaymentDto = this.duePaymentList.get(Integer.parseInt(request.getParameter("num")));
		int paidMoney = Integer.parseInt(request.getParameter("money"));
		int customerMoney = 0;

		try {
			Resource resourceCustomer = resourceLoader.getResource("classpath:File//CustomerBank.txt");
			String customerBankPath = resourceCustomer.getURI().getPath();
			File file = new File(customerBankPath);
			Scanner scanner = new Scanner(file);
			String tempMoney = scanner.next();
			customerMoney = Integer.parseInt(tempMoney);
			if (paidMoney > customerMoney) {
				throw new LackMoneyException();
			}
			customerMoney = customerMoney - paidMoney;
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(customerMoney);
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException | InputMismatchException e) {
			throw new FailPaymentExcaption();
		}

		try {
			Resource resourceCustomer = resourceLoader.getResource("classpath:File//InsuranceBank.txt");
			String customerBankPath = resourceCustomer.getURI().getPath();
			File file = new File(customerBankPath);
			Scanner scanner = new Scanner(file);
			String tempMoney = scanner.next();
			int insuranceMoney = Integer.parseInt(tempMoney);
			insuranceMoney = customerMoney + paidMoney;
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(insuranceMoney);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException | InputMismatchException e) {
			throw new FailPaymentExcaption();
		}
		this.tempPaymentList = new ArrayList<Payment>();
		Payment payment = new Payment();
		payment.setPaymentID(UUID.randomUUID().toString());
		payment.setCustomerID(this.customer.getCustomerID());
		payment.setCustomerName(this.customer.getName());
		payment.setCustomerPhoneNum(this.customer.getPhoneNum());
		payment.setAccountNum(this.accountDto.getAccountNum());
		payment.setCardOrBankName(this.accountDto.getBankName());
		payment.setInsuranceFee(paidMoney);
		payment.setInsuranceName(duePaymentDto.getInsuranceName());
		payment.setPaidDate(LocalDate.now());
		payment.setContractID(duePaymentDto.getContractID());
		payment.setInsuranceType(this.insuranceDao.retriveInsuranceType(duePaymentDto.getInsuranceID()));
		this.tempPaymentList.add(payment);
		if (this.paymentDao.insertPayment(payment) == 1) {
			this.paymentDao.commit();
		} else {
			throw new PersistenceException();
		}

		ContractAccountDto contractAccountDto = new ContractAccountDto();
		contractAccountDto.setContractID(duePaymentDto.getContractID());
		contractAccountDto.setUnpaidFee(duePaymentDto.getUnpaidFee() - paidMoney);
		if (this.contractDao.updateUnpaidFee(contractAccountDto) == 1) {
			this.contractDao.commit();
		} else {
			throw new PersistenceException();
		}

		this.unpaidFeeDto = new UnpaideFeeDto();
		this.unpaidFeeDto.setUnpaidFee(contractAccountDto.getUnpaidFee());
		return true;
	}

	@Override
	public UnpaideFeeDto getUnpaideFeeDto() {
		return this.unpaidFeeDto;
	}

	@Override
	public boolean printPayment() {
		try {
			Resource resourceCustomer = resourceLoader.getResource("classpath:File//InsuranceBank.txt");
			String customerBankPath = resourceCustomer.getURI().getPath();
			File file = new File(customerBankPath);
			FileWriter fileWriter = new FileWriter(file);
			for(Payment payment: this.tempPaymentList) {
			String tempPrint = "보험이름: "+ payment.getInsuranceName()+"보험종류: "+ payment.getInsuranceType() +"카드사/은행명: " + payment.getCardOrBankName() + "카드/계좌번호: " +payment.getAccountNum() + "납부금액: "+ payment.getInsuranceFee() + "납부일: "+payment.getPaidDate();
			
			fileWriter.write(tempPrint);
			fileWriter.flush();
			}
			
			fileWriter.close();
			
			JEditorPane text = new JEditorPane("file:///" + customerBankPath);
			text.print(null, null, true, null, null, false);
		} catch (IOException | PrinterException e) {
			throw new FailPaymentExcaption();
		}
		return true;
	}

}
