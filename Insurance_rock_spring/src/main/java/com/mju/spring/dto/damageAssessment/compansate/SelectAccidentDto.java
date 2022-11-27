package com.mju.spring.dto.damageAssessment.compansate;

import java.time.LocalDate;

public class SelectAccidentDto {
	private String customerName;
	private LocalDate accidentDate;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalDate getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(LocalDate accidentDate) {
		this.accidentDate = accidentDate;
	}
	
}
