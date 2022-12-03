package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.ContractProvisionDto;
import com.mju.spring.dto.damageAssessment.compansate.SelectContractDto;
import com.mju.spring.dto.damageAssessment.compansate.UpdateContractDto;
import com.mju.spring.entity.Contract;

@Repository
public class ContractDaoImpl implements ContractDao{

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String SelectNameAndPhoneNum = "ContractMapper.selectNameAndPhoneNum";
	private static final String SelectContractProvision = "ContractMapper.selectContractProvision";
	private static final String UpdateContractProvisionFee = "ContractMapper.updateContractProvisionFee";

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
	public int create(Contract applyContract) {
		return 0;
	}
	

}
