package com.riskcare.bigdata.rest.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riskcare.bigdata.rest.services.IRiskService;
import com.riskcare.bigdata.util.ListDTO;
import com.riskcare.bigdata.util.RiskBookWrapper;



@Controller
public class RiskRestController {

	private final Logger logger = LoggerFactory.getLogger(RiskRestController.class);
	
	private IRiskService riskService;
	
	public void setRiskService (IRiskService riskService){
		this.riskService = riskService;		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/bigdata/getRiskAmt", produces = "application/json")
    @ResponseBody
    public ListDTO<RiskBookWrapper> getRiskAmt(HttpServletRequest request, @RequestBody RiskBookWrapper riskBookWrapper) throws Exception {
			
		logger.info("inside getRiskAmt");
		
		return riskService.getRiskAmt(riskBookWrapper);		
	}	
}