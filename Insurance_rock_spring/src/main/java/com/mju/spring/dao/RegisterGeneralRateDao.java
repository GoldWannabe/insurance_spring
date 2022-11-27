package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.entity.Insurance;

public interface RegisterGeneralRateDao {

	public int create(Insurance insurance);

	public void commit();

	public List<Double> retriveById(String insuranceID);

	public int delete(String insuranceID);

}
