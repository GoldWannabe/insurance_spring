package com.mju.spring.DAO;

import java.util.List;

public interface GeneralRateDao {

	public List<Double> retriveGeneralRate(String insuranceID);

}
