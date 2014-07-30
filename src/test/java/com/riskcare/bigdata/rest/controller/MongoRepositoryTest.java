package com.riskcare.bigdata.rest.controller;

import java.io.IOException;
import java.text.ParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Mongo;
import com.riskcare.bigdata.mongo.domain.BookRisk;
import com.riskcare.bigdata.repos.mongo.RiskBookMongoRepository;
import com.riskcare.bigdata.rest.services.MongoRiskService;
import com.riskcare.bigdata.util.ListDTO;
import com.riskcare.bigdata.util.RiskBookWrapper;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ArtifactStoreBuilder;
import de.flapdoodle.embed.mongo.config.DownloadConfigBuilder;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.extract.UserTempNaming;
import de.flapdoodle.embed.process.runtime.Network;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class MongoRepositoryTest {
	
	private static final String LOCALHOST = "localhost";
    private static final String DB_NAME = "itest";
    private static final int MONGO_TEST_PORT = 27029;

    @Autowired
	private MongoRiskService mongoRiskService;
    @Autowired
	private RiskBookMongoRepository riskBookMongoRepository;

    private static MongodProcess mongoProcess;
    private static Mongo mongo;
    
    private static MongoTemplate template;
    private static MongodExecutable mongodExecutable ;
 

    @BeforeClass
    public static void initializeDB() throws IOException {
    	System.setProperty("spring.profiles.active", "mongodb");
    	Command command = Command.MongoD;
      	 
    	IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
        								.defaults(command)
        								.artifactStore(new ArtifactStoreBuilder()
        									.defaults(command)
        									.download(new DownloadConfigBuilder()
        									.defaultsForCommand(command))
        									.executableNaming(new UserTempNaming()))
        								.build();

    	IMongodConfig mongodConfig = new MongodConfigBuilder()
         								.version(Version.Main.PRODUCTION)
         								.net(new Net(MONGO_TEST_PORT, Network.localhostIsIPv6()))
         								.build();
    	 
    	MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);
    	mongodExecutable = runtime.prepare(mongodConfig);
        mongoProcess = mongodExecutable.start();

        mongo = new Mongo(LOCALHOST, MONGO_TEST_PORT);
        
        template = new MongoTemplate(mongo, DB_NAME);
        
    }

    @AfterClass
    public static void shutdownDB() throws InterruptedException {
    	
    	mongodExecutable.stop();
        mongoProcess.stop();
    }

    
    @Before
    public void setUp() throws Exception {
    	mongoRiskService = new MongoRiskService();
    	mongoRiskService.setRiskBookMongoRepository(riskBookMongoRepository);
    	
    	riskBookMongoRepository.save(createBook("BOOK_01","UK",21000.00));
		
    	riskBookMongoRepository.save(createBook("BOOK_01","USA",22000.00));
		
    	riskBookMongoRepository.save(createBook("BOOK_02","UK",23000.00));

    	riskBookMongoRepository.save(createBook("BOOK_02","USA",24000.00));
    }

    @After
    public void tearDown() throws Exception {      
    	template.dropCollection(BookRisk.class);
    }
   

    @Test
    public void testGetRiskAmt() throws Exception {   	
    	
    	RiskBookWrapper riskBookWrapper = new RiskBookWrapper();
    	riskBookWrapper.setBookId("BOOK_01");
    	riskBookWrapper.setCountry("UK");
    	
		ListDTO<RiskBookWrapper> listDto = mongoRiskService.getRiskAmt(riskBookWrapper);
		
		
		Assert.assertEquals(listDto.getRecords().get(0).getRiskAmt(),new Double(21000.0));
    }
    
    @Test
    public void testNoRecFoundException() {   	
    	
//    	try{
//		
//    		mongoRiskService.getRiskAmtByBookAndCountry("BOOK_001", "UK");
//    		Assert.fail("Test should throw exception");
//    	}catch(Exception e){
//    		
//    	}
		
    }
    
    @Test
    public void testMultipleRecFoundException() {   	
    	
//    	try{
//		
//    		riskBookMongoRepository.save(createBook("BOOK_01","UK",23000.00));
//    		
//    		mongoRiskService.getRiskAmtByBookAndCountry("BOOK_01", "UK");
//    		Assert.fail("Test should throw exception");
//    	}catch(Exception e){
//    		
//    	}
		
    }

	private BookRisk createBook(String bookId, String country, double riskAmt) throws ParseException {
		
			
		final BookRisk bookRisk = new BookRisk();
		bookRisk.setBookId(bookId);
		bookRisk.setCountry(country);
		
		bookRisk.setRiskAmt(riskAmt);
		
		return bookRisk;
	}

}
