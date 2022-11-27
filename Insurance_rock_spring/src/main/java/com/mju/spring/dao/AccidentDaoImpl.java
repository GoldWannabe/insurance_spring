package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.SelectAccidentDto;
import com.mju.spring.entity.Accident;

@Repository
public class AccidentDaoImpl implements AccidentDao{
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	
	private static final String insertAccident = "AccidentMapper.insertAccident";
	private static final String selectAccident = "AccidentMapper.selectAccident";
	private static final String updatePayComplation = "AccidentMapper.updatePayComplation";
	
	@Override
	public void insertAccident(Accident accident) {
		sqlSession.selectOne(insertAccident, accident);
	}

	@Override
	public List<Accident> retriveNameAndDate(SelectAccidentDto selectAccidentDto) {
		return sqlSession.selectList(selectAccident, selectAccidentDto);
		
	}

	@Override
	public void updatePaycompleted(Accident accident) {
		sqlSession.selectList(updatePayComplation, accident);
	}


}
