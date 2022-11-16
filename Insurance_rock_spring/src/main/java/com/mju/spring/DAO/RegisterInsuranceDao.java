package com.mju.spring.DAO;

import org.springframework.stereotype.Repository;

import com.mju.spring.Entity.Insurance;

@Repository
public interface RegisterInsuranceDao {

	public Insurance retriveName(String insuranceName);

	public int create(Insurance insurance);

}
