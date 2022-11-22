package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.mju.spring.Entity.Insurance;

@Repository
public class RegisterInsuranceDaoImpl implements RegisterInsuranceDao {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String SelectName = "RegisterInsuranceMapper.selectName";
	private static final String Create = "RegisterInsuranceMapper.create";

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

}
