package com.riskcare.bigdata.repos.mongo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.riskcare.bigdata.mongo.domain.BookRisk;

public interface RiskBookMongoRepository extends MongoRepository<BookRisk, String>{
	
	@Query("{'date':?0,'book_id':?1}")
	public List<BookRisk> findByDateAndBookId(Date date,String bookId);

}
