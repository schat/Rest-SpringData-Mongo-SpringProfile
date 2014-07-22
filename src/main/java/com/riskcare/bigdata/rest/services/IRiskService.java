package com.riskcare.bigdata.rest.services;


public interface IRiskService {

	public Double getRiskAmtByBookAndDate(String bookId, String date) throws Exception;
}
