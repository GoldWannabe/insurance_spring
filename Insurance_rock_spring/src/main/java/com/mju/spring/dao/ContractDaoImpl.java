package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.contractTeam.contractManagement.SelectContractManagementDto;
import com.mju.spring.dto.damageAssessment.compansate.ContractProvisionDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectContractDto;
import com.mju.spring.dto.damageAssessment.compansate.UpdateContractDto;
import com.mju.spring.dto.policyholder.feePayment.PaymentDto;
import com.mju.spring.dto.policyholder.feePayment.PolicyholderDto;
import com.mju.spring.entity.Contract;

@Repository
public class ContractDaoImpl implements ContractDao{

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String SelectNameAndPhoneNum = "ContractMapper.selectNameAndPhoneNum";
	private static final String SelectContractProvision = "ContractMapper.selectContractProvision";
	private static final String UpdateContractProvisionFee = "ContractMapper.updateContractProvisionFee";
	private static final String SelectContractManagement = "ContractMapper.selectContractManagement";
	private static final String DeleteContractManagement = "ContractMapper.deleteContractManagement";
	private static final String Create = "ContractMapper.create";
	private static final String SelectContractById = "ContractMapper.selectContractById";
	private static final String UpdateRenew = "ContractMapper.updateRenew";
	private static final String SelectPayment = "ContractMapper.selectPayment";
	
	@Override
	public List<Contract> retriveNameAndPhoneNum(SelectContractDto selectContractDto) {
		return sqlSession.selectList(SelectNameAndPhoneNum, selectContractDto);
	}

	@Override
	public ContractProvisionDto retriveContract(String contractID) {
		return sqlSession.selectOne(SelectContractProvision, contractID);
	}

	@Override
	public void updateContractProvisionFee(UpdateContractDto updateContractDto) {
		sqlSession.update(UpdateContractProvisionFee, updateContractDto);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public int create(Contract contract) {
		return sqlSession.insert(Create, contract);
	}

	@Override
	public List<Contract> retriveContractManagement(SelectContractManagementDto selectContractManagementDto) {
		return sqlSession.selectList(SelectContractManagement, selectContractManagementDto);
	}

	@Override
	public void deleteContractManagement(String contractID) {
		sqlSession.delete(DeleteContractManagement, contractID);		
	}

	@Override
	public Contract retriveContractById(String contractID) {
		return sqlSession.selectOne(SelectContractById, contractID);
	}

	@Override
	public int updateRenew(Contract contract) {
		return sqlSession.update(UpdateRenew, contract);

	}

	@Override
	public List<PaymentDto> retrivePayment(PolicyholderDto policyholderDto) {
		return sqlSession.selectList(SelectPayment, policyholderDto);
	}
	

}
