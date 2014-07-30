package com.riskcare.bigdata.rest.services;

import com.riskcare.bigdata.util.ListDTO;
import com.riskcare.bigdata.util.RiskBookWrapper;


public interface IRiskService {
	
	public ListDTO<RiskBookWrapper> getRiskAmt(RiskBookWrapper riskBookWrapper) throws Exception;
}
