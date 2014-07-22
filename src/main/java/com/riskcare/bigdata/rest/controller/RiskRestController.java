package com.riskcare.bigdata.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riskcare.bigdata.rest.services.IRiskService;



@Controller
public class RiskRestController {

	private final Logger logger = LoggerFactory.getLogger(RiskRestController.class);
	
	private IRiskService riskService;
	
	public void setRiskService (IRiskService riskService){
		this.riskService = riskService; 
		// TODO : this will be mongodb based service , 
	}
	
    @RequestMapping(method = RequestMethod.GET, value = "/bigdata/getRiskAmtByBookAndDate/{bookId}/{date}")
    public @ResponseBody Double getRiskAmtByBookAndDate(
    		@PathVariable("bookId") String bookId,
    		@PathVariable("date") String date) throws Exception{
         
    	logger.info("inside getRiskAmtByBookAndDate , bookId : " + bookId + " , date : " + date);
    	
    	return (riskService.getRiskAmtByBookAndDate(bookId, date));
    
    }

}