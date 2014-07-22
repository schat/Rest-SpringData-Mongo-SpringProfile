package com.riskcare.bigdata.mongo.domain;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class InsertData {

	public static void main(String[] args) throws UnknownHostException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
	        
	    DB database = client.getDB("rc_bigdata");
		DBCollection collection = database.getCollection("total_sensitivity");
		
		String dateStr1 = "10032010";
		Date date1 = sdf.parse(dateStr1);
		BasicDBObject record1 = new BasicDBObject("book_id", "BOOK_01").append("date", date1).append("risk_amt",21000.00);
		
		collection.save(record1);
		
		String dateStr2 = "10042010";
		Date date2 = sdf.parse(dateStr2);
		BasicDBObject record2 = new BasicDBObject("book_id", "BOOK_01").append("date", date2).append("risk_amt",22000.00);
		
		collection.save(record2);
		
		String dateStr3 = "10032010";
		Date date3 = sdf.parse(dateStr3);
		BasicDBObject record3 = new BasicDBObject("book_id", "BOOK_02").append("date", date3).append("risk_amt",23000.00);
		
		collection.save(record3);
		
		String dateStr4 = "10042010";
		Date date4 = sdf.parse(dateStr4);
		BasicDBObject record4 = new BasicDBObject("book_id", "BOOK_02").append("date", date4).append("risk_amt",24000.00);
		
		collection.save(record4);
		
		String dateStr5 = "10062012";
		Date date5 = sdf.parse(dateStr5);
		BasicDBObject record5 = new BasicDBObject("book_id", "BOOK_03").append("date", date5).append("risk_amt",24000.00);
		
		collection.save(record5);
	}

	/*@RequestMapping(method = RequestMethod.GET, value = "/bigdata/fetch")
    public @ResponseBody Double getRiskAmtByBook(@RequestParam(value="empId",required=true) String bookId) throws UnknownHostException {

		//TODO : Can multiple entries possible for a bookId, handle accordingly or exception
		//TODO : Exception if no entry for the bookid passed 
		
		System.out.println("Received REST call from to fetch book id :  ");
        logger.info("Received REST call from to fetch book id :  " + bookId);
        
        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
        
        DB database = client.getDB("course");
		DBCollection collection = database.getCollection("hello");       
		
		DBObject query = new BasicDBObject("type",bookId);
		
		DBObject object = collection.findOne(query);
		
		Double riskAmount = (Double)object.get("riskAmount");
        
        return riskAmount;
    }*/
  
}
