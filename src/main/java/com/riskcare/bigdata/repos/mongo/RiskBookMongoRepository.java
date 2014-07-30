package com.riskcare.bigdata.repos.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.riskcare.bigdata.mongo.domain.BookRisk;

public interface RiskBookMongoRepository extends MongoRepository<BookRisk, String>{
	
	@Query("{'rplBook':?0,'country':?1}")
	public List<BookRisk> findByBookIdAndCountry(String bookId,String country);
	
	@Query("{'rplBook':?0}")
	public List<BookRisk> findByBookId(String bookId);
	
	@Query("{'country':?0}")
	public List<BookRisk> findByCountry(String country);

}
