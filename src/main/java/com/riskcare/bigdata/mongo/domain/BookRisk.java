package com.riskcare.bigdata.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "map_reduced_risk")
public class BookRisk {
		
	@Id
    private String id;

	@Field("rplBook")
	private String bookId;
	
	@Field("country")
	private String country;
	
	@Field("risk_amt")
	private Double riskAmt;
   
    public BookRisk() {}

    public BookRisk(String bookId,String country, Double riskAmt) {
        this.bookId = bookId;
        this.country=country;
        this.riskAmt = riskAmt;
    }

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

    @Override
    public String toString() {
        return String.format("Risk[id=%s, book_id='%s' , country ='%s']",id, bookId,country);
    }

    
    
}
