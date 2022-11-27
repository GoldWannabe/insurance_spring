package com.mju.spring.dao;

import org.springframework.stereotype.Repository;

import com.mju.spring.entity.Provision;

@Repository
public interface ProvisionDao {
	public void inserNeProvision(Provision provision);
	
}
