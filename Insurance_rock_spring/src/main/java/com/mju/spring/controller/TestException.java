package com.mju.spring.controller;

import org.apache.ibatis.exceptions.PersistenceException;

public class TestException extends PersistenceException{
	public TestException() {
		super("134253465");
	}
}
