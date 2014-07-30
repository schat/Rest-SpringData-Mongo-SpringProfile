package com.riskcare.bigdata.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.riskcare.bigdata.rest.services.IRiskService;
import com.riskcare.bigdata.rest.services.MongoRiskService;
import com.riskcare.bigdata.util.ListDTO;
import com.riskcare.bigdata.util.RiskBookWrapper;

import static org.easymock.EasyMock.*;

public class MyControllerTest {
	
	private IRiskService iRiskService;
	private HttpServletRequest req;
	private RiskRestController riskRestController;
	private String bookId = "BOOK_ID";
	private String country = "UK";
	private Double riskAmt = 10.0;
	
	@Before
    public void setup() {
		iRiskService = createMock(MongoRiskService.class);
		req = createMock(HttpServletRequest.class);
		riskRestController = new RiskRestController();
	    riskRestController.setRiskService(iRiskService);
    }
	
	@Test
	public void test() throws Exception {		
               
       RiskBookWrapper riskBookWrapper_in = new RiskBookWrapper();
       riskBookWrapper_in.setBookId(bookId);
       riskBookWrapper_in.setCountry(country);
        
       RiskBookWrapper riskBookWrapper_out = new RiskBookWrapper();
       riskBookWrapper_out.setBookId(bookId);
       riskBookWrapper_out.setCountry(country);
       riskBookWrapper_out.setRiskAmt(riskAmt);
            
       List<RiskBookWrapper> riskBookWrapperList = new ArrayList<RiskBookWrapper>();
       riskBookWrapperList.add(riskBookWrapper_out);
        
       ListDTO<RiskBookWrapper> riskBookWrapperListDto = new ListDTO<RiskBookWrapper>(riskBookWrapperList.size(), riskBookWrapperList);
        
	   expect(iRiskService.getRiskAmt(riskBookWrapper_in)).andReturn(riskBookWrapperListDto).times(1);
        
       replay(iRiskService);
        
       ListDTO<RiskBookWrapper> riskBookWrapperListDto1 = riskRestController.getRiskAmt(req, riskBookWrapper_in);
       Assert.assertEquals(riskBookWrapperListDto1.getRecords().get(0).getRiskAmt(), riskAmt);

	}

}
