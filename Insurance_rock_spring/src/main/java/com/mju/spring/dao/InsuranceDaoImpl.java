package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Insurance;

@Repository
public class InsuranceDaoImpl implements InsuranceDao {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectName = "InsuranceMapper.selectName";
	private static final String SelectGeneralName = "InsuranceMapper.selectGeneralName";
	private static final String SelectHouseName = "InsuranceMapper.selectHouseName";
	private static final String SelectGeneralType = "InsuranceMapper.selectGeneralType";
	private static final String SelectHouseType = "InsuranceMapper.selectHouseType";
	private static final String Create = "InsuranceMapper.create";
	private static final String SelectInsuranceType = "InsuranceMapper.selectInsuranceType";



	@Override
	public String retriveName(String insuranceName) {
		return sqlSession.selectOne(SelectName, insuranceName);
	}
	
	@Override
	public Insurance retriveGeneralName(String insuranceName) {
		return sqlSession.selectOne(SelectGeneralName,insuranceName);
	}
	
	@Override
	public Insurance retriveHouseName(String insuranceName) {
		return sqlSession.selectOne(SelectHouseName, insuranceName);
	}


	@Override
	public List<Insurance> retriveGeneralInsuranceList(String insuranceType) {
		return sqlSession.selectList(SelectGeneralType, insuranceType);
	}

	@Override
	public List<Insurance> retriveHouseInsuranceList(String insuranceType) {
		return sqlSession.selectList(SelectHouseType, insuranceType);
	}

	@Override
	public int create(Insurance insurance) {
		return sqlSession.insert(Create, insurance);
	}
	
	@Override
	public void commit() {
		this.sqlSession.commit();
	}
	
	@Override
	public String retriveInsuranceType(String insuranceID) {
		return sqlSession.selectOne(SelectInsuranceType, insuranceID);
	}






//	@Override
//	public List<Insurance> retriveInsuranceList(String insuranceType) {
//		return sqlSession.selectList(SelectType, insuranceType);
//	}
}
