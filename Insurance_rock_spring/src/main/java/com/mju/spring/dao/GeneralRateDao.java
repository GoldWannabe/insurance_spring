package com.mju.spring.dao;

import java.util.List;

public interface GeneralRateDao {

	public List<Double> retriveGeneralRate(String insuranceID);

}
