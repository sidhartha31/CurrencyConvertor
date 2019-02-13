package com.sidharth.forexrate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forexrates")
public class ForexRateAPI {
	
	static final String FOREX_CONVERSION_URI = "https://free.currencyconverterapi.com/api/v6/convert";
	
	@RequestMapping(value = "/rates", method = RequestMethod.GET, produces = {"application/json","application/xml"})
	public ResponseEntity getForexRates(@RequestHeader("from") String from, @RequestHeader("to") String to) {
		
		try {
			System.out.println("In Forex Rate..");
			HttpClient client = HttpClientBuilder.create().build();
			
			String finalURI = FOREX_CONVERSION_URI + "?q="+from+"_"+to+"&compact=ultra&apiKey=8a99ef32ef685ef20641"; 
			System.out.println("URI for Forex Rate: "+finalURI);
			HttpGet httpGet = new HttpGet(finalURI);
			httpGet.addHeader("accept","application/json");
			HttpResponse response = client.execute(httpGet);
			double forexRate = -1;
			if(from.isEmpty() || to.isEmpty()) {
				return new ResponseEntity(forexRate, HttpStatus.BAD_REQUEST);
			}
			else {
				HttpEntity entity = response.getEntity();
			    String stringOutput = EntityUtils.toString(entity);
			    
			    JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(stringOutput);
				
			    forexRate = (double) json.get(from+"_"+to);
			    System.out.println("Forex Rate: "+forexRate);
			    
				return new ResponseEntity(forexRate, HttpStatus.OK);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
