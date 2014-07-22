package com.riskcare.bigdata.mongo.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "total_sensitivity")
public class BookRisk {
		
	@Id
    private String id;

	@Field("book_id")
	private String bookId;
	
	@Field("date")
	private Date date;
	
	@Field("risk_amt")
	private Double riskAmt;
   
    public BookRisk() {}

    public BookRisk(String bookId,Date date, Double riskAmt) {
        this.bookId = bookId;
        this.date=date;
        this.riskAmt = riskAmt;
    }

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getRiskAmt() {
		return riskAmt;
	}

	public void setRiskAmt(Double riskAmt) {
		this.riskAmt = riskAmt;
	}

    @Override
    public String toString() {
        return String.format("Risk[id=%s, book_id='%s' , date ='%s']",id, bookId,date);
    }

    
    
}
