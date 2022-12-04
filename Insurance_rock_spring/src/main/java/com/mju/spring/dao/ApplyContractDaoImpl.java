package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;

import com.mju.spring.entity.Contract;

@Repository
public class ApplyContractDaoImpl implements ApplyContractDao {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String Create = "ApplyContractMapper.create";
	private static final String SelectAll = "ApplyContractMapper.selectAll";
	private static final String Delete = "ApplyContractMapper.delete";

	@Override
	public int create(Contract applyContract) {
		return sqlSession.insert(Create, applyContract);
	}

	@Override
	public List<ApplyContractDto> retriveApplyContractList() {
		return sqlSession.selectList(SelectAll);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public int delete(String contractID) {
		return sqlSession.delete(Delete, contractID);
	}

}
