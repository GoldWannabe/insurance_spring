package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.entity.Insurance;

public interface GeneralRateDao {

	public List<Double> retriveGeneralRate(String insuranceID);

	public int create(Insurance insurance);

	public void commit();

}
