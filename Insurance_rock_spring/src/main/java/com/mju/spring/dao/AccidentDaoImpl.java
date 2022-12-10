package com.mju.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mju.spring.dto.damageAssessment.compansate.SelectAccidentDto;
import com.mju.spring.entity.Accident;

@Repository
public class AccidentDaoImpl implements AccidentDao {
	private SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	private static final String Create = "AccidentMapper.create";
	private static final String SelectAccident = "AccidentMapper.selectAccident";
	private static final String UpdatePayComplation = "AccidentMapper.updatePayComplation";
	private static final String SelectAccidentByContractId = "AccidentMapper.selectAccidentByContractId";
	private static final String UpdateAccidentInfo = "AccidentMapper.updateAccidentInfo";

	@Override
	public void create(Accident accident) {
		sqlSession.insert(Create, accident);
	}

	@Override
	public List<Accident> retriveNameAndDate(SelectAccidentDto selectAccidentDto) {
		return sqlSession.selectList(SelectAccident, selectAccidentDto);

	}

	@Override
	public void updatePaycompleted(Accident accident) {
		sqlSession.update(UpdatePayComplation, accident);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public List<Accident> retriveAccidentByContractId(String contractID) {
		return sqlSession.selectList(SelectAccidentByContractId, contractID);
	}

	@Override
	public boolean updateAccidentInfo(Accident accident) {
		try {			
			sqlSession.update(UpdateAccidentInfo, accident);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

}
