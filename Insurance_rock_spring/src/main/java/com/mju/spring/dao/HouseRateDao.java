package com.mju.spring.dao;

import java.util.List;

import com.mju.spring.entity.Insurance;

public interface HouseRateDao {

	public List<Double> retriveHouseName(String insuranceID);

	public int create(Insurance insurance);

	public void commit();

}
