package com.mju.spring.dao;

import com.mju.spring.entity.Insurance;

public interface RegisterGeneralRateDao {

	public int create(Insurance insurance);

	public void commit();

}
