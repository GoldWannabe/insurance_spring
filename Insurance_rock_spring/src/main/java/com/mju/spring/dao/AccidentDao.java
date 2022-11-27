package com.mju.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Accident;

@Repository
public interface AccidentDao {

	public void insertAccident(Accident accident);

	public List<Accident> retriveNameAndDate(String[] nameAndDate);

	public void updatePaycompleted(Accident accident );
	
}
