package com.riskcare.bigdata.rest.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.bigdata.mongo.domain.BookRisk;
import com.riskcare.bigdata.repos.mongo.RiskBookMongoRepository;


public class MongoRiskService implements IRiskService{
	
	private final Logger logger = LoggerFactory.getLogger(MongoRiskService.class);
		
	private RiskBookMongoRepository riskBookMongoRepository;
		
	public void setRiskBookMongoRepository(RiskBookMongoRepository riskBookRepository) {
		this.riskBookMongoRepository = riskBookRepository;
	}	
  
	public Double getRiskAmtByBookAndDate(String bookId, String dateStr) throws Exception {
		
		//Validation : if date is not in correct format say 123
		logger.info("inside MongoRiskService");	
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
				
		Date date = sdf.parse(dateStr);	
		
		logger.info("date : " + date);	
		
		List<BookRisk> bookRiskList = riskBookMongoRepository.findByDateAndBookId(date, bookId);
		
		if(bookRiskList.size() == 0)
			throw new Exception("No record for the bookid and date passed");
		
		if(bookRiskList.size() > 1)
			throw new Exception("Multiple records exist for the bookid and date passed");
		
		return bookRiskList.get(0).getRiskAmt();
	}


} 