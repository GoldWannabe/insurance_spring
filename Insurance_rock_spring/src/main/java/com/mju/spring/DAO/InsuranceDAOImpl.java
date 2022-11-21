package com.mju.spring.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.Entity.Insurance;

@Repository
public class InsuranceDAOImpl implements InsuranceDAO {
	
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String SelectName = "InsuranceMapper.selectName";
	private static final String SelectGeneralType = "InsuranceMapper.selectGeneralType";
	private static final String SelectHouseType = "InsuranceMapper.selectHouseType";

	@Override
	public Insurance retriveName(String insuranceName) {
		return sqlSession.selectOne(SelectName, insuranceName);
	}

	@Override
	public List<Insurance> retriveGeneralInsuranceList(String insuranceType) {
		return sqlSession.selectList(SelectGeneralType, insuranceType);
	}

	@Override
	public List<Insurance> retriveHouseInsuranceList(String insuranceType) {
		return sqlSession.selectList(SelectHouseType, insuranceType);
	}

//	@Override
//	public List<Insurance> retriveInsuranceList(String insuranceType) {
//		return sqlSession.selectList(SelectType, insuranceType);
//	}
}
