package com.mju.spring.service.contractTeam;

import java.util.List;

import com.mju.spring.entity.Contract;

public interface UnderwritingService {

	List<Contract> getApply();

	List<Contract> getRenew();

}
