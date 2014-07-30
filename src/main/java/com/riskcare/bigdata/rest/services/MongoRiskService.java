package com.riskcare.bigdata.rest.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.bigdata.mongo.domain.BookRisk;
import com.riskcare.bigdata.repos.mongo.RiskBookMongoRepository;
import com.riskcare.bigdata.util.ListDTO;
import com.riskcare.bigdata.util.RiskBookWrapper;


public class MongoRiskService implements IRiskService{
	
	private final Logger logger = LoggerFactory.getLogger(MongoRiskService.class);
		
	private RiskBookMongoRepository riskBookMongoRepository;
		
	public void setRiskBookMongoRepository(RiskBookMongoRepository riskBookRepository) {
		this.riskBookMongoRepository = riskBookRepository;
	}	
  
	private List<BookRisk> getRiskAmtByBookAndCountry(String bookId, String country) throws Exception {
		
		logger.info("inside getRiskAmtByBookAndCountry");	
			
		return riskBookMongoRepository.findByBookIdAndCountry(bookId,country);	
		
	}
	
	private List<BookRisk> getRiskAmt() throws Exception {
		
		logger.info("inside getRiskAmt");	
			
		return riskBookMongoRepository.findAll();	
		
	}

	public ListDTO<RiskBookWrapper> getRiskAmt(RiskBookWrapper riskBookWrapper) {
		
		logger.info("inside getRiskAmt");	
				
//		if(riskBookWrapper.getBookId() == null && riskBookWrapper.getCountry() == null)
//			return ListDTO.failure("Both BookId and Country can't be null");
		
		List<BookRisk> bookRiskList = new ArrayList<BookRisk>();
		try{
			if(riskBookWrapper.getBookId() == null && riskBookWrapper.getCountry() == null)
				bookRiskList = getRiskAmt();
			else if(riskBookWrapper.getBookId() != null && riskBookWrapper.getCountry() != null)
				bookRiskList = getRiskAmtByBookAndCountry(riskBookWrapper.getBookId(),riskBookWrapper.getCountry());
			else if(riskBookWrapper.getBookId() != null && riskBookWrapper.getCountry() == null)
				bookRiskList = getRiskAmtByBook(riskBookWrapper.getBookId());
			else
				bookRiskList = getRiskAmtByCountry(riskBookWrapper.getCountry());	
		}catch(Exception e){
			return ListDTO.failure( e.getMessage());
		}
		
		return convertBookRiskList(bookRiskList);
		
	}
	
	private ListDTO<RiskBookWrapper> convertBookRiskList(List<BookRisk> bookRiskList) {
		
		List<RiskBookWrapper> riskBookWrapperList = new ArrayList<RiskBookWrapper>();
		
		for(BookRisk br: bookRiskList){
			RiskBookWrapper riskBookWrapper = new RiskBookWrapper();
			riskBookWrapper.setBookId(br.getBookId());
			riskBookWrapper.setCountry(br.getCountry());
			riskBookWrapper.setRiskAmt(br.getRiskAmt());
			riskBookWrapperList.add(riskBookWrapper);
		}
		
		return new ListDTO<RiskBookWrapper>(riskBookWrapperList.size(), riskBookWrapperList);
	}

	private List<BookRisk> getRiskAmtByBook(String bookId) throws Exception {
		
		logger.info("inside getRiskAmtByBook");	
			
		return riskBookMongoRepository.findByBookId(bookId);		
	}

	private List<BookRisk> getRiskAmtByCountry(String country) throws Exception {
		
		logger.info("inside getRiskAmtByCountry");	
			
		return riskBookMongoRepository.findByCountry(country);		
	}

} 