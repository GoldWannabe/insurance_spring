package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto;

@Repository
public class ApplyContractDaoImpl implements ApplyContractDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String SelectAll = "ApplyContractMapper.selectAll";
	

	@Override
	public List<ApplyContractDto> retriveApplyContractList() {
		return sqlSession.selectList(SelectAll);
	}

}
