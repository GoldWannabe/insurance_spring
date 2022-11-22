package com.mju.spring.DAO;

import com.mju.spring.Entity.Insurance;

public interface RegisterGeneralRateDao {

	public int create(Insurance insurance);

	public void commit();

}
