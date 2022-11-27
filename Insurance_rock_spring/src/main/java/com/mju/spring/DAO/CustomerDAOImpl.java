package com.mju.spring.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	// CustomerMapper 생성해야 함
	private static final String SelectName = "CustomerMapper.selectName";

}
