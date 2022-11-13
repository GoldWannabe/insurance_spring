package com.mju.spring.Service;

import com.mju.spring.DTO.InsuranceDTO;
import com.mju.spring.DTO.InsuranceDTO.EInsurance;

public interface InsuranceDesignService {

	InsuranceDTO getinsuranceType(EInsurance insuranceType);

}
