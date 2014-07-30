package com.riskcare.bigdata.util;

import java.io.Serializable;


import org.codehaus.jackson.map.annotate.JsonSerialize;


public class RiskBookWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String bookId;
    private String country;
    private Double riskAmt;
    
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getRiskAmt() {
		return riskAmt;
	}
	public void setRiskAmt(Double riskAmt) {
		this.riskAmt = riskAmt;
	}
   
}
