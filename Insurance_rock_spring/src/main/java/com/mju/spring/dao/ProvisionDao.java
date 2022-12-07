package com.mju.spring.dao;

import com.mju.spring.entity.Provision;

public interface ProvisionDao {
	public void insertProvision(Provision provision);

	public void commit();

	
}
