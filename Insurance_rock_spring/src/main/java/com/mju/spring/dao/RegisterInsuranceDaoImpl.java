package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto;
import com.mju.spring.entity.Insurance;

@Repository
public class RegisterInsuranceDaoImpl implements RegisterInsuranceDao {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String SelectName = "RegisterInsuranceMapper.selectName";
	private static final String Create = "RegisterInsuranceMapper.create";
	private static final String SelectAll = "RegisterInsuranceMapper.selectAll";
	private static final String SelectGeneral = "RegisterInsuranceMapper.selectGeneral";
	private static final String SelectHouse = "RegisterInsuranceMapper.selectHouse";
	private static final String Delete = "RegisterInsuranceMapper.delete";

	@Override
	public String retriveName(String insuranceName) {
		return sqlSession.selectOne(SelectName, insuranceName);
	}

	@Override
	public int create(Insurance insurance) {

		return sqlSession.insert(Create, insurance);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public List<RegisterInsuranceDto> retriveRegisterInsuranceList() {
		return sqlSession.selectList(SelectAll);
	}

	@Override
	public Insurance retriveGeneralName(String insuranceName) {
		return sqlSession.selectOne(SelectGeneral, insuranceName);
	}

	@Override
	public Insurance retriveHouseName(String insuranceName) {
		return sqlSession.selectOne(SelectHouse, insuranceName);
	}

	@Override
	public int delete(String insuranceID) {
		return sqlSession.delete(Delete, insuranceID);
	} 


}
